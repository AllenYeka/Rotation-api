package wrq.rotation.gateway.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import wrq.rotation.gateway.mapper.UserMapper;
import wrq.rotation.gateway.model.po.User;

import java.time.Duration;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/test01",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> test01(){
        List<User> users=userMapper.getAllUser();
        return Flux.just(users.toArray(new User[0])).delayElements(Duration.ofMillis(500));
    }
}
