package wrq.rotation.gateway.mapper;
import wrq.rotation.gateway.model.po.User;
import java.util.List;

public interface UserMapper {
    User getUserByName(String username);
    User getUserById(int id);
    List<User> getAllUser();
    int insertUser(User user);
    List<String> getAllUsername();
}
