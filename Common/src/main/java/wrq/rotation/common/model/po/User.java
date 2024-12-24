package wrq.rotation.common.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private boolean userType;//用户类型(false-0-管理员 true-1-普通用户)
    private String bio;
    private String email;
    private String avatar;
    private boolean status;//状态(false-0-停用  true-1-启用)
    public User(String username, String password,boolean userType, String bio, String email, String avatar) {
        this.username = username;
        this.password = password;
        this.userType=userType;
        this.bio = bio;
        this.email = email;
        this.avatar = avatar;
        this.status=status;
    }
}
