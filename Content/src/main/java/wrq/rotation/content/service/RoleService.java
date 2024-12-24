package wrq.rotation.content.service;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.content.model.po.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRole(int pageNo);
    int getRoleCount();
    int deleteRole(int id);
    int editRole(Role role);
    int addRole(Role role);
}
