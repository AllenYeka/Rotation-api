package wrq.rotation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wrq.rotation.model.dto.MenuItemDto;
import wrq.rotation.model.po.MenuItem;
import wrq.rotation.service.MenuService;
import java.util.List;

@RestController
@RequestMapping("/content/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menuList")
    public List<MenuItemDto> menuItemList(){
        return menuService.menuList();
    }
}
