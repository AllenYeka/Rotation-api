package wrq.rotation.media.filter;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import wrq.rotation.media.mapper.UserMapper;
import wrq.rotation.media.model.dto.MediaResponse;
import wrq.rotation.media.utils.JWTUtil;
import wrq.rotation.media.model.po.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        MediaResponse mediaResponse =new MediaResponse();
        mediaResponse.setStatus("0606500");
        mediaResponse.setOperation("token校验");

        /* token为空 */
        String token=request.getHeader("Authorization");
        if("".equals(token)||token==null) {
            mediaResponse.setMessage("token为空");
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(mediaResponse));
            return;
        }

        /* token不合法 */
        DecodedJWT payload=null;
        try{
            payload = JWTUtil.verifyToken(token);
        }catch (Exception exception) {
            mediaResponse.setMessage("token不合法");
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(mediaResponse));
            return;
        }

        /* 判断用户ID是否存在 */
        String username=payload.getClaim("username").asString();
        int uid=Integer.valueOf(payload.getClaim("uid").asString());
        User user=userMapper.queryUserById(uid);
        if(user==null) {
            mediaResponse.setMessage("用户不存在");
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(mediaResponse));
            return;
        }
        if(!user.getUsername().equals(username)){
            mediaResponse.setMessage("用户ID和用户名不匹配");
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(mediaResponse));
            return;
        }

        /* 保存Authencation对象到安全上下文 */
        String password = payload.getClaim("password").asString();//密文
        String auths = payload.getClaim("auths").asString();
        Collection<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(auths);//权限和角色
        Authentication authentication=new UsernamePasswordAuthenticationToken(username,password,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
