import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.common.model.po.User;
import wrq.rotation.gateway.GatewayApplication;
import wrq.rotation.gateway.mapper.UserMapper;
import wrq.rotation.gateway.service.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(classes = GatewayApplication.class)
public class GatewayTest {
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void test01(){
        Map<String,List<Integer>> idList=userService.getIdList(1);
        System.out.println(idList);
    }
}
