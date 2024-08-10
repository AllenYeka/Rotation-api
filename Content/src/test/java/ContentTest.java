import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wrq.rotation.content.ContentApplication;
import wrq.rotation.content.mapper.TipMapper;
import wrq.rotation.content.model.dto.TipDto;
import wrq.rotation.content.model.po.Tip;
import wrq.rotation.content.model.pojo.Comment;
import wrq.rotation.content.service.MenuService;
import wrq.rotation.content.service.RoleService;
import wrq.rotation.content.service.TipService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ContentApplication.class)
public class ContentTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private TipMapper tipMapper;
    @Autowired
    private TipService tipService;
    @Test
    public void test01(){
        Tip tip=new Tip();
        tip.setTitle("标题");
        tip.setUid(1);
        tip.setUsername("jojo");
        tip.setDescription("描述");
        tip.setTipPicture("tip图片");
        tip.setAvatarUrl("头像");
        tipService.addTip(tip);
    }
}
