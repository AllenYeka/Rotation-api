package wrq.rotation.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wrq.rotation.model.po.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String status;
    private String token;
    private User user;
}
