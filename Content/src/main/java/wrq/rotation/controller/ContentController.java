package wrq.rotation.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import wrq.rotation.model.dto.RoleResponse;
import wrq.rotation.model.po.Role;
import wrq.rotation.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RoleService roleService;

    @GetMapping("/getRoleByPageNo")
    public List<Role> getRoleByPageNo(int pageNo){
        if(stringRedisTemplate.opsForValue().get("roles"+pageNo)!=null){
            System.out.println("roles"+pageNo+"--->redis缓存");
            List<Role> roleList= JSON.parseArray(stringRedisTemplate.opsForValue().get("roles"+pageNo),Role.class);
            System.out.println(roleList);
            return roleList;
        }
        else
            return roleService.getRole(pageNo);
    }

    @GetMapping("/getRoleCount")
    public int getRoleCount(){
        return roleService.getRoleCount();
    }

    @GetMapping("/deleteRole/{id}")
    public RoleResponse deleteRole(@PathVariable int id,int pageNo){
        stringRedisTemplate.delete("roles"+pageNo);
        return roleService.deleteRole(id);
    }

    @PostMapping("/editRole")
    public Role editRole(@RequestBody Role role,int pageNo){
        stringRedisTemplate.delete("roles"+pageNo);
        return roleService.editRole(role);
    }

    @PostMapping("/addRole")
    public RoleResponse addRole(@RequestBody Role role,int roleCount){
        return roleService.addRole(role,roleCount);
    }
}
