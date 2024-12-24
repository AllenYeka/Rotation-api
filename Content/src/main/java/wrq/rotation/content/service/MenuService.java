package wrq.rotation.content.service;
import wrq.rotation.content.model.dto.MenuItemDto;

import java.util.List;

public interface MenuService {
    List<MenuItemDto> menuList(boolean userType);
}
