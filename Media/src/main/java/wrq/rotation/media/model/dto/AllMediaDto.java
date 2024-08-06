package wrq.rotation.media.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllMediaDto {
    private int id;
    private String objectName;
    private String username;
    private String createTime;
}
