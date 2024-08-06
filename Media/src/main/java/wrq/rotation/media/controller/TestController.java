package wrq.rotation.media.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
public class TestController {
    @GetMapping("/test01")
    public String test01(){
        return "杰洛.齐贝林";
    }
}
