package wrq.rotation.gateway.mapper;
import org.apache.ibatis.annotations.Param;
import wrq.rotation.common.model.po.User;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    User queryUser(Integer uid);
    User queryUserByUsername(String username);//登录
    List<Map> queryAllUser();
    int insertUser(User user);
    int insertOauthUser(User user);
    User existUser(Integer uid);
    List<Map> queryConcernById(Integer uid);
    List<Map> queryFanById(Integer uid);
    List<Integer> queryCollectId(Integer uid);
    List<Integer> queryConcernId(Integer uid);
    int concern(@Param("uid")Integer uid,@Param("concernId")Integer concernId);
    int cancelConcern(@Param("uid")Integer uid,@Param("concernId")Integer concernId);
    int collect(@Param("uid")Integer uid,@Param("mediaId")Integer mediaId);
    int cancelCollect(@Param("uid")Integer uid,@Param("mediaId")Integer mediaId);
}
