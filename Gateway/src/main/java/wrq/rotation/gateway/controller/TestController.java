package wrq.rotation.gateway.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import wrq.rotation.common.model.po.User;
import wrq.rotation.gateway.mapper.UserMapper;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/test01",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map> test01(){
        List<Map> users=userMapper.queryAllUser();
        return Flux.just(users.toArray(new Map[users.size()])).delayElements(Duration.ofMillis(500));
    }
}
