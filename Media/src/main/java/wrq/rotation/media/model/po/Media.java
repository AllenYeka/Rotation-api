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
    private Integer id;
    private String objectName;
    private String objectUrl;
    private String createTime;
    private Integer stockingType;
    private Integer creator;
    private String nickname;
    private String avatar;
    private boolean status;
}
