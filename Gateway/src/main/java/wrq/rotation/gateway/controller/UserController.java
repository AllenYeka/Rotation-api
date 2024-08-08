package wrq.rotation.gateway.controller;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import wrq.rotation.gateway.mapper.UserMapper;
import wrq.rotation.gateway.model.dto.UserDto;
import wrq.rotation.gateway.model.po.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getAllUser")
    public List<UserDto> getAllUser(){
        List<User> users=userMapper.getAllUser();
        List<UserDto> userDtos=new ArrayList<>();
        for(int i=0;i<users.size();i++){
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(users.get(i),userDto);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @GetMapping("/getUserByName")
    public User getUserByName(String username){
        return userMapper.getUserByName(username);
    }

}
