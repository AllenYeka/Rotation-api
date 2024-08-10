package wrq.rotation.content.model.dto;
import lombok.Data;
import wrq.rotation.content.model.pojo.Comment;
import java.util.List;

@Data
public class TipDto {
    private int tipId;
    private String title;
    private int uid;
    private String username;
    private String avatarUrl;
    private String description;
    private String tipPicture;
    List<Comment> comments;
}
