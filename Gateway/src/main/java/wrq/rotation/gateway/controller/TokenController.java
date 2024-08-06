package wrq.rotation.gateway.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrq.rotation.gateway.mapper.UserMapper;
import wrq.rotation.gateway.utils.JWTUtil;
import wrq.rotation.gateway.model.po.User;

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
        int uid=userMapper.getUserByName(user.getUsername()).getId();
        Map<String,String> payload=new HashMap<>();
        payload.put("uid",String.valueOf(uid));
        payload.put("username",user.getUsername());
        payload.put("password","oauth2_login");
        payload.put("auths","ROLE_ZONGTONG,总统大权");
        return JWTUtil.getToken(payload);
    }
}
