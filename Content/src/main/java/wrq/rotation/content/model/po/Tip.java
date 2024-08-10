package wrq.rotation.content.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tip {
    private int tipId;
    private String title;
    private int uid;
    private String username;
    private String avatarUrl;
    private String description;
    private String comments;
    private String tipPicture;
}
