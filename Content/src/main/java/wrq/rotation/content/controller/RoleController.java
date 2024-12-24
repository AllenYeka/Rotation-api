package wrq.rotation.content.controller;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.content.model.po.Role;
import wrq.rotation.content.service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/content")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/getRole")
    public ResponseDTO getRole(Integer pageNo){
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            Integer roleCount=roleService.getRoleCount();
            Map<String,Object> dataList=new HashMap<>();
            List<Role> roleList=roleService.getRole(pageNo);
            dataList.put("roleCount",roleCount);
            dataList.put("roleList",roleList);
            responseDTO.setDataList(dataList);
        }catch (RuntimeException e){
            responseDTO.setMsg(e.toString());
            responseDTO.setStatus(500);
            return responseDTO;
        }
        return responseDTO;
    }

    @PostMapping("/deleteRole")
    public ResponseDTO deleteRole(Integer uid){
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            roleService.deleteRole(uid);
        }catch (RuntimeException e){
            responseDTO.setMsg(e.toString());
            responseDTO.setStatus(500);
            return responseDTO;
        }
        return responseDTO;
    }

    @PostMapping("/editRole")
    public ResponseDTO editRole(@RequestBody Role role){
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            roleService.editRole(role);
        }catch (RuntimeException e){
            responseDTO.setMsg(e.toString());
            responseDTO.setStatus(500);
            return responseDTO;
        }
        return responseDTO;
    }

    @PostMapping("/addRole")
    public ResponseDTO addRole(@RequestBody Role role){
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            roleService.addRole(role);
        }catch (RuntimeException e){
            responseDTO.setMsg(e.toString());
            responseDTO.setStatus(500);
            return responseDTO;
        }
        return responseDTO;
    }
}
