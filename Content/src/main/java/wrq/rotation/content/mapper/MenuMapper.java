package wrq.rotation.content.mapper;
import wrq.rotation.content.model.po.MenuItem;

import java.util.List;

public interface MenuMapper {
    List<MenuItem> menuList(boolean userType);
}
