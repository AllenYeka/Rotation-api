package wrq.rotation.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wrq.rotation.model.po.User;

@FeignClient(name = "gateway")
@Component
public interface GatewayClient {
    @GetMapping("/getUserByName")
    public User getUserByName(@RequestParam("username") String username);
}
