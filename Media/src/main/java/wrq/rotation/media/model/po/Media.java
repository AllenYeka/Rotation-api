package wrq.rotation.media.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    private int id;
    private String objectName;
    private String username;
    private String createTime;
    private String userAvatarUrl;
    private String objectUrl;

    public Media(String username, String userAvatarUrl) {
        this.username = username;
        this.userAvatarUrl = userAvatarUrl;
    }
}
