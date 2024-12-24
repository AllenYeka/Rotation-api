package wrq.rotation.common.config;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import wrq.rotation.common.model.po.User;
import wrq.rotation.common.model.pojo.MyUserDetail;

import java.util.Map;

public class UserContext {
    public static Integer getUserId() {
        Map<String,Object> userInfo=(Map<String,Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Integer) userInfo.get("uid");
    }
    public static boolean getUserType(){
        Map<String,Object> userInfo=(Map<String,Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (boolean) userInfo.get("userType");
    }
}
