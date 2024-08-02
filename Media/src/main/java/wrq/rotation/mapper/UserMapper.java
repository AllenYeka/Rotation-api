package wrq.rotation.mapper;
import org.apache.ibatis.annotations.Param;
import wrq.rotation.model.po.Media;
import wrq.rotation.model.po.User;
import java.util.List;

public interface UserMapper {
    User queryUser(String username);
    List<User> queryConcern(@Param("concern") String[] concern);
    List<User> queryFans(@Param("fans") String[] fans);
}
