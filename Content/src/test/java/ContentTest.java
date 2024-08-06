import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wrq.rotation.content.ContentApplication;
import wrq.rotation.content.service.MenuService;
import wrq.rotation.content.service.RoleService;

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
