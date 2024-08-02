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
    private User user;
    private List<Media> media;
}
