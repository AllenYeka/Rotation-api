package wrq.rotation.content.model.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int commentId;
    private int uid;
    private String username;
    private String createTime;
    private String content;
    private String avatarUrl;
}
