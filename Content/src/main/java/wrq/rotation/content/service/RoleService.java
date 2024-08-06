package wrq.rotation.content.service;
import wrq.rotation.content.model.dto.RoleResponse;
import wrq.rotation.content.model.po.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRole(int pageNo);
    int getRoleCount();
    RoleResponse deleteRole(int id);
    Role editRole(Role role);
    RoleResponse addRole(Role role,int roleCount);
}
