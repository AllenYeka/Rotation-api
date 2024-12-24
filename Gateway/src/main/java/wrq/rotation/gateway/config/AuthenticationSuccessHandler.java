package wrq.rotation.gateway.config;
import com.alibaba.fastjson.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import wrq.rotation.common.config.UserContext;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.common.model.pojo.MyUserDetail;
import wrq.rotation.common.util.JWTUtil;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        ResponseDTO responseDTO=new ResponseDTO();
        /* 获取用户信息 */
        MyUserDetail myUserDetail=(MyUserDetail)authentication.getPrincipal();
        if(!myUserDetail.getUser().isStatus()){
            responseDTO.setMsg("用户被禁用");
            responseDTO.setStatus(504);
        }

        else{
            /* 生成token */
            Map<String,String> payload=new HashMap<>();
            payload.put("uid",String.valueOf(myUserDetail.getUser().getId()));
            payload.put("userType",String.valueOf(myUserDetail.getUser().isUserType()));
            String token= JWTUtil.getToken(payload);
            System.out.println(token);
            Map<String,Object> dataList=new HashMap<>();
            dataList.put("token",token);
            dataList.put("userInfo",myUserDetail.getUser());
            responseDTO.setDataList(dataList);
        }

        String result= JSON.toJSONString(responseDTO);
        DataBuffer responseData = response.bufferFactory().wrap(result.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(responseData));
    }
}
