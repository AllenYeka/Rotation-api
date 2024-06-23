package wrq.rotation.mapper;
import wrq.rotation.model.po.User;

public interface UserMapper {
    User queryUser(String username);
}
