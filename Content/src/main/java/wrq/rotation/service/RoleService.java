package wrq.rotation.service;
import com.github.pagehelper.PageInfo;
import wrq.rotation.model.dto.RoleResponse;
import wrq.rotation.model.po.Role;
import java.util.List;

public interface RoleService {
    List<Role> getRole(int pageNo);
    int getRoleCount();
    RoleResponse deleteRole(int id);
    Role editRole(Role role);
    RoleResponse addRole(Role role,int roleCount);
}
