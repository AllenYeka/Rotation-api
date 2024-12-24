package wrq.rotation.content.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private Integer tipId;
    private Integer creator;
    private String nickname;
    private LocalDateTime createTime;
    private String content;
    private String avatar;
}
