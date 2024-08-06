package wrq.rotation.media.mapper;
import org.apache.ibatis.annotations.Param;
import wrq.rotation.media.model.po.User;
import java.util.List;

public interface UserMapper {
    User queryUser(String username);
    User queryUserById(int id);
    List<User> queryConcern(@Param("concern") String[] concern);
    List<User> queryFans(@Param("fans") String[] fans);
}
