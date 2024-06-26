package wrq.rotation.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem{
    private int id;
    private String menuItemName;
    private int parentId;
    private int isLeaf;
    private String route;
    private String icon;
}
