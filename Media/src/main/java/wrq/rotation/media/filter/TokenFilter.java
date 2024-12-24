package wrq.rotation.media.filter;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.common.util.JWTUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatus(401);

        /* token校验 */
        String token=request.getHeader("Authorization");
        DecodedJWT payload=null;
        try{
            payload = JWTUtil.verifyToken(token);
        }catch (Exception exception) {
            responseDTO.setMsg("token错误"+exception.getMessage());
            PrintWriter writer=response.getWriter();
            writer.print(JSON.toJSONString(responseDTO));
            return;
        }

        /* 保存Authencation对象到安全上下文 */
        int uid=Integer.valueOf(payload.getClaim("uid").asString());
        boolean userType=Boolean.valueOf(payload.getClaim("userType").asString());
        Map<String,Object> userInfo=new HashMap<>();
        userInfo.put("uid",uid);
        userInfo.put("userType",userType);
        Authentication authentication=new UsernamePasswordAuthenticationToken(userInfo,null,null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
