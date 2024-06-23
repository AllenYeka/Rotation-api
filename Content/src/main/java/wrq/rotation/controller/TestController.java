package wrq.rotation.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
@RefreshScope
public class TestController {
    //@Value("${wrq.rotation.content.name}")
    private String name="胡桃";

    @GetMapping("/test01")
    public String test01(){
        return name;
    }
}
