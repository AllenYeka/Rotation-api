package wrq.rotation.content.mapper;
import wrq.rotation.content.model.po.User;

public interface UserMapper {
    User queryUser(String username);
    User queryUserById(int id);
}
