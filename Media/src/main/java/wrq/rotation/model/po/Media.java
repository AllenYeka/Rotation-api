package wrq.rotation.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    private int id;
    private String objectName;
    private String username;
    private String userAvatarUrl;
    private String objectUrl;

    public Media(String username, String userAvatarUrl) {
        this.username = username;
        this.userAvatarUrl = userAvatarUrl;
    }
}
