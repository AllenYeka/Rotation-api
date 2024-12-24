package wrq.rotation.content.service.impl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.content.mapper.RoleMapper;
import wrq.rotation.content.model.po.Role;
import wrq.rotation.content.service.RoleService;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    public static int PAGE_SIZE=8;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRole(int pageNo) {
        PageHelper.startPage(pageNo,PAGE_SIZE);
        List<Role> roleList=roleMapper.queryAllRole();
        return roleList;
    }

    @Override
    public int getRoleCount() {
        return roleMapper.getRoleCount();
    }

    @Override
    public int deleteRole(int id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public int editRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.insertRole(role);
    }
}
