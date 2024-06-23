package wrq.rotation.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;
    private String name;
    private String sex;
    private String standName;
    private String standPower;
    private String motto;
}
