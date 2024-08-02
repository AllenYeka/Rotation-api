package wrq.rotation.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wrq.rotation.model.po.Media;
import wrq.rotation.model.po.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMediaDto {
    private User user;//用户信息
    private List<Media> media;//作品
    private List<Media> collection;//收藏
    private List<User> concern;//关注
    private List<User> fans;//粉丝
}
