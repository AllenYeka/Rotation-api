package wrq.rotation.gateway.config;
import com.alibaba.fastjson.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import wrq.rotation.gateway.model.pojo.MyUserDetail;
import wrq.rotation.gateway.model.dto.ResponseData;
import wrq.rotation.gateway.utils.JWTUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();

        /* 获取用户信息 */
        MyUserDetail myUserDetail=(MyUserDetail)authentication.getPrincipal();

        /* 创造jwt */
        Map<String,String> payload=new HashMap<>();
        payload.put("uid",String.valueOf(myUserDetail.getUser().getId()));
        payload.put("username",authentication.getName());
        payload.put("password",authentication.getCredentials().toString());//密文
        StringBuffer authsBuffer=new StringBuffer();
        for(GrantedAuthority auth:authentication.getAuthorities())
            authsBuffer.append(auth+",");
        String auths=authsBuffer.substring(0,authsBuffer.length()-1);
        payload.put("auths",auths);
        String token= JWTUtil.getToken(payload);

        String result= JSON.toJSONString(new ResponseData("OK",token,myUserDetail.getUser()));
        DataBuffer data = response.bufferFactory().wrap(result.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(data));
    }
}
