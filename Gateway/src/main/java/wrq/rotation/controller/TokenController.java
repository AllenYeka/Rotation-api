package wrq.rotation.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wrq.rotation.utils.JWTUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {
    @GetMapping("/getAccessToken")
    public String getToken(String username){
        Map<String,String> payload=new HashMap<>();
        payload.put("username",username);
        payload.put("password","oauth2_login");
        payload.put("auths","ROLE_ZONGTONG,总统大权");
        payload.put("oauth2","true");
        return JWTUtil.getToken(payload);
    }
}
