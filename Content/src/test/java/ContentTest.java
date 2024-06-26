import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import wrq.rotation.ContentApplication;
import wrq.rotation.mapper.RoleMapper;
import wrq.rotation.model.po.Role;
import wrq.rotation.service.MenuService;
import wrq.rotation.service.RoleService;
import wrq.rotation.util.JWTUtil;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = ContentApplication.class)
public class ContentTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Test
    public void test01(){
        System.out.println(menuService.menuList());
    }
}
