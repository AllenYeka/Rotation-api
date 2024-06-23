import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import wrq.rotation.GatewayApplication;
import wrq.rotation.mapper.UserMapper;

@SpringBootTest(classes = GatewayApplication.class)
public class GatewayTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        System.out.println(BCrypt.hashpw("032418",BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("032418",BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("032418",BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("032418",BCrypt.gensalt()));
    }
}
