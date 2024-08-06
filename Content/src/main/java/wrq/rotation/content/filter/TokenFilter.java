package wrq.rotation.content.filter;
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
import wrq.rotation.content.model.dto.RoleResponse;
import wrq.rotation.content.mapper.UserMapper;
import wrq.rotation.content.model.po.User;
import wrq.rotation.content.util.JWTUtil;

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
        System.out.println(request.getRequestURI());
        if(request.getRequestURI().startsWith("/content/test")) {
            filterChain.doFilter(request, response);
            return;
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        RoleResponse roleResponse=new RoleResponse();
        roleResponse.setStatus("0606500");
        roleResponse.setOperation("token校验");

        /* token为空 */
        String token=request.getHeader("Authorization");
        if("".equals(token)||token==null) {
            roleResponse.setMessage("token为空");
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(roleResponse));
            return;
        }

        /* token不合法 */
        DecodedJWT payload=null;
        try{
            payload = JWTUtil.verifyToken(token);
        }catch (Exception exception) {
            roleResponse.setMessage("token不合法"+exception.getMessage());
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(roleResponse));
            return;
        }

        /* 判断用户ID是否存在 */
        String username=payload.getClaim("username").asString();
        int uid=Integer.valueOf(payload.getClaim("uid").asString());
        User user=userMapper.queryUserById(uid);
        if(user==null) {
            roleResponse.setMessage("用户不存在");
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(roleResponse));
            return;
        }
        if(!user.getUsername().equals(username)){
            roleResponse.setMessage("用户ID和用户名不匹配");
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(roleResponse));
            return;
        }

        /* 保存Authencation对象到安全上下文 */
        String password = payload.getClaim("password").asString();
        String auths = payload.getClaim("auths").asString();
        Collection<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(auths);//权限和角色
        Authentication authentication=new UsernamePasswordAuthenticationToken(username,password,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
