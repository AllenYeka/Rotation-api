package wrq.rotation.content.mapper;
import wrq.rotation.content.model.po.User;

public interface UserMapper {
    User queryUser(String username);
    User queryUserById(int id);
    int updateUser(User user);
    int deleteConcern(User user);
    int deleteFans(User user);
    int deleteCollection(User user);
}
