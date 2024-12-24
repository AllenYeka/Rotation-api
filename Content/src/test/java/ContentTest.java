import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.content.ContentApplication;
import wrq.rotation.content.service.MenuService;
import wrq.rotation.content.service.RoleService;
import wrq.rotation.content.service.TipService;
import wrq.rotation.content.util.MinioUtil;

@SpringBootTest(classes = ContentApplication.class)
public class ContentTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private TipService tipService;
    @Autowired
    private MinioUtil minioUtil;
    @Test
    public void test01(){
        System.out.println(minioUtil.fileList("tip").get(0).objectName());
    }
}
