package wrq.rotation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wrq.rotation.mapper.UserMapper;
import wrq.rotation.model.dto.ResponseData;
import wrq.rotation.model.po.User;
import wrq.rotation.utils.JWTUtil;
import java.awt.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
