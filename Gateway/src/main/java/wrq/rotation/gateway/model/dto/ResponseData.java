package wrq.rotation.gateway.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wrq.rotation.gateway.model.po.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String status;
    private String token;
    private User user;
}
