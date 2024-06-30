package wrq.rotation.service.impl;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import wrq.rotation.mapper.RoleMapper;
import wrq.rotation.model.dto.RoleResponse;
import wrq.rotation.model.po.Role;
import wrq.rotation.service.RoleService;

import java.time.Duration;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    public static int PAGE_SIZE=8;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRole(int pageNo) {
        System.out.println("roles"+pageNo+"--->访问数据库");
        PageHelper.startPage(pageNo,PAGE_SIZE);
        List<Role> roleList=roleMapper.queryAllRole();
        stringRedisTemplate.opsForValue().set("roles"+pageNo, JSON.toJSONString(roleList), Duration.ofMinutes(1));
        return roleList;
    }

    @Override
    public int getRoleCount() {
        return roleMapper.getRoleCount();
    }

    @Override
    public RoleResponse deleteRole(int id) {
        RoleResponse roleResponse=new RoleResponse();
        int result=roleMapper.deleteRoleById(id);
        if (result==1){
            roleResponse.setStatus("0604200");
            roleResponse.setOperation("删除");
            roleResponse.setMessage("删除成功");
        }
        else{
            roleResponse.setStatus("0604500");
            roleResponse.setOperation("删除");
            roleResponse.setMessage("删除失败");
        }
        return roleResponse;
    }

    @Override
    public Role editRole(Role role) {
        if (roleMapper.updateRole(role)==1)
            return role;
        else
            return null;
    }

    @Override
    public RoleResponse addRole(Role role,int roleCount) {
        RoleResponse roleResponse=new RoleResponse();
        int result=roleMapper.insertRole(role);
        if (result==1){
            roleResponse.setStatus("0604200");
            roleResponse.setOperation("新增");
            roleResponse.setMessage("新增成功");
            stringRedisTemplate.delete("roles"+(int)Math.ceil((double) roleCount/PAGE_SIZE));
        }
        else{
            roleResponse.setStatus("0604500");
            roleResponse.setOperation("新增");
            roleResponse.setMessage("新增失败");
        }
        return roleResponse;
    }
}
