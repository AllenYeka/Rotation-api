package wrq.rotation.content.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wrq.rotation.content.model.po.MenuItem;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {
    private MenuItem menuItem;
    private List<MenuItemDto> sonItem;
}
