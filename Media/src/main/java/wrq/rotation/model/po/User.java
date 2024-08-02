package wrq.rotation.model.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String bio;
    private String email;
    private String avatarUrl;

    public User(String username, String password, String bio, String email, String avatarUrl) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }
}
