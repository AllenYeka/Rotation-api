package wrq.rotation.media.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.common.model.po.User;

@FeignClient(name = "gateway")
@Component
public interface GatewayClient {
    @GetMapping("/getCreatorBaseInfoAndConcern")
    public ResponseDTO getCreatorBaseInfoAndConcern(@RequestParam("uid") Integer uid);
}
