package wrq.rotation.config;
import com.alibaba.fastjson.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import wrq.rotation.model.dto.ResponseData;
import wrq.rotation.model.po.User;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class AuthenticationFailHandler implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        String result= JSON.toJSONString(new ResponseData("ERROR", exception.getMessage().toString(),null));
        DataBuffer data = response.bufferFactory().wrap(result.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(data));
    }
}
