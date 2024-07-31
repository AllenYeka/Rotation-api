package wrq.rotation.mapper;
import wrq.rotation.model.po.User;
import java.util.List;

public interface UserMapper {
    User getUserByName(String username);
    List<User> getAllUser();
    int insertUser(User user);
    List<String> getAllUsername();
}
