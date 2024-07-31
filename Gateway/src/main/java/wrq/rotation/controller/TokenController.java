package wrq.rotation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrq.rotation.mapper.UserMapper;
import wrq.rotation.model.po.User;
import wrq.rotation.utils.JWTUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TokenController {
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/getAccessToken")
    public String getToken(@RequestBody User user){
        List<String> username=userMapper.getAllUsername();
        user.setPassword("oauth2_login");
        if(!username.contains(user.getUsername())){
            new Thread(()->{
               userMapper.insertUser(user);
            }).start();
        }
        Map<String,String> payload=new HashMap<>();
        payload.put("username",user.getUsername());
        payload.put("password","oauth2_login");
        payload.put("auths","ROLE_ZONGTONG,总统大权");
        payload.put("oauth2","true");
        return JWTUtil.getToken(payload);
    }
}
