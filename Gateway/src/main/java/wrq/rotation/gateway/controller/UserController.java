package wrq.rotation.gateway.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrq.rotation.common.config.UserContext;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.common.model.po.User;
import wrq.rotation.common.util.JWTUtil;
import wrq.rotation.gateway.mapper.UserMapper;
import wrq.rotation.gateway.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/getAllUser")
    public ResponseDTO getAllUser(){
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(userService.getAllUser());
        }catch (RuntimeException e){
            responseDTO.setMsg(e.toString());
            responseDTO.setStatus(500);
        }
        return responseDTO;
    }

    @PostMapping("/getAccessToken")
    public ResponseDTO getToken(@RequestBody User user){
        ResponseDTO responseDTO=new ResponseDTO();
        if(!userService.existUser(user.getId()))//第三方用户信息未注册到本地
            userService.registerOauthUser(user);
        Map<String,String> payload=new HashMap<>();
        payload.put("uid",String.valueOf(user.getId()));
        payload.put("userType",String.valueOf(true));
        responseDTO.setDataList(JWTUtil.getToken(payload));
        return responseDTO;
    }

    @GetMapping("/getIdList")
    public ResponseDTO getIdList(Integer uid){//我收藏的资源Id、我关注的用户Id
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(userService.getIdList(uid));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @GetMapping("/getUserConcern")
    public ResponseDTO getUserConcern(Integer uid){//关注列表
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(userService.getUserConcern(uid));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @GetMapping("/getUserFans")
    public ResponseDTO getUserFans(Integer uid){//粉丝列表
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(userService.getUserFans(uid));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @GetMapping("/getCreatorBaseInfoAndConcern")
    public ResponseDTO getCreatorBaseInfoAndConcern(@RequestParam("uid") Integer uid){//获取创作者基本信息和关注列表
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            Map<String,Object> dataList=new HashMap<>();
            dataList.put("baseInfo",userService.getUser(uid));
            dataList.put("concernList",userService.getUserConcern(uid));
            responseDTO.setDataList(dataList);
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @PostMapping("/updateConcern")
    public ResponseDTO updateConcern(Integer uid,Integer concernId,boolean deleted){//关注或取关
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            userService.updateConcern(uid, concernId, deleted);
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @PostMapping("/updateCollect")
    public ResponseDTO updateCollect(Integer uid,Integer mediaId,boolean deleted){//收藏或取消收藏
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            userService.updateCollect(uid, mediaId, deleted);
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }
}
