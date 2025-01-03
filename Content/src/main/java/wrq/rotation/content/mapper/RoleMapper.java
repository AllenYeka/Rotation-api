package wrq.rotation.content.mapper;
import wrq.rotation.content.model.po.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> queryAllRole();
    int getRoleCount();
    int deleteRole(Integer uid);
    int updateRole(Role role);
    int insertRole(Role role);
}
