import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import wrq.rotation.GatewayApplication;
import wrq.rotation.mapper.UserMapper;
import wrq.rotation.model.dto.UserDto;
import wrq.rotation.model.po.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = GatewayApplication.class)
public class GatewayTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        User user=userMapper.getUserById(1);
    }
}
