package wrq.rotation.service;
import wrq.rotation.model.dto.MenuItemDto;
import wrq.rotation.model.po.MenuItem;
import java.util.List;

public interface MenuService {
    List<MenuItemDto> menuList();
}
