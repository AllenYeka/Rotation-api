package wrq.rotation.gateway.model.po;
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
    private String collection;
    private String concern;
    private String fans;

    public User(String username, String password, String bio, String email, String avatarUrl, String collection, String concern, String fans) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.collection = collection;
        this.concern = concern;
        this.fans = fans;
    }
}
