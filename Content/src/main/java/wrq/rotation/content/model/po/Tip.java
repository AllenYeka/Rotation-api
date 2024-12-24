package wrq.rotation.content.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tip {
    private Integer id;
    private String title;
    private String description;
    private String cover;
    private Integer creator;
    private String nickname;
    private String avatar;
}
