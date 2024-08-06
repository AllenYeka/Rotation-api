package wrq.rotation.gateway.controller;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import wrq.rotation.gateway.mapper.UserMapper;
import wrq.rotation.gateway.model.dto.UserDto;
import wrq.rotation.gateway.model.po.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private ThreadPoolExecutor pool;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getAllUser")
    public List<UserDto> getAllUser(){
        List<User> users=userMapper.getAllUser();
        List<UserDto> userDtos=new ArrayList<>();
        for(int i=0;i<users.size();i++){
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(users.get(i),userDto);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @GetMapping("/getUserByName")
    public User getUserByName(String username){
        return userMapper.getUserByName(username);
    }

    @PostMapping("/updateConcernAndFans")//新增关注和粉丝
    public void updateConcernAndFans(int UID,int targetUID){
        pool.submit(()->{
            User targetUser=userMapper.getUserById(targetUID);
            String fans=targetUser.getFans();
            System.out.println("".equals(fans));
            if(fans!=null&&!"".equals(fans)) {
                String newFans = fans.substring(0, fans.length() - 1)+","+UID+"]";
                targetUser.setFans(newFans);
                userMapper.updateUser(targetUser);
            }
            else{
                fans="["+UID+"]";
                targetUser.setFans(fans);
                userMapper.updateUser(targetUser);
            }
        });
        User user=userMapper.getUserById(UID);
        String concern=user.getConcern();
        if(concern!=null&&!"".equals(concern)) {
            String newConcern = concern.substring(0, concern.length() - 1)+","+targetUID+"]";
            user.setConcern(newConcern);
            userMapper.updateUser(user);
        }
        else{
            concern="["+targetUID+"]";
            user.setConcern(concern);
            userMapper.updateUser(user);
        }
    }

    @PostMapping("/deleteConcernAndFans")//删除关注和粉丝
    public void deleteConcernAndFans(int UID,int targetUID){
        pool.submit(()->{
            User targetUser=userMapper.getUserById(targetUID);
            String fans=targetUser.getFans();
            if(fans.substring(1,fans.length()-1).length()==1) {
                targetUser.setFans(null);
                userMapper.deleteFans(targetUser);
            }
            else{
                String[] new_fans=fans.substring(1,fans.length()-1).split(",");
                List<String> new_fans_List= Arrays.stream(new_fans).collect(Collectors.toList());
                new_fans_List.remove(String.valueOf(targetUID));
                String[] result=new_fans_List.toArray(new String[0]);
                StringBuilder response=new StringBuilder("[");
                for(String str:result)
                    response.append(str+",");
                String finally_response=response.substring(0,response.length()-1)+"]";
                targetUser.setFans(finally_response.toString());
                userMapper.deleteFans(targetUser);
            }
        });
        User user=userMapper.getUserById(UID);
        String concern=user.getConcern();
        if(concern.substring(1,concern.length()-1).length()==1) {
            user.setConcern(null);
            userMapper.deleteConcern(user);
        }
        else{
            String[] new_concern=concern.substring(1,concern.length()-1).split(",");
            List<String> new_concern_List= Arrays.stream(new_concern).collect(Collectors.toList());
            new_concern_List.remove(String.valueOf(targetUID));
            String[] result=new_concern_List.toArray(new String[0]);
            StringBuilder response=new StringBuilder("[");
            for(String str:result)
                response.append(str+",");
            String finally_response=response.substring(0,response.length()-1)+"]";
            user.setConcern(finally_response.toString());
            userMapper.deleteConcern(user);
        }
    }


    @PostMapping("/updateCollection")//更新收藏
    public void updateCollection(int UID,int mediaID){
        User user=userMapper.getUserById(UID);
        String collection=user.getCollection();
        if(collection!=null&&!"".equals(collection)) {
            String newCollection = collection.substring(0, collection.length() - 1)+","+mediaID+"]";
            user.setCollection(newCollection);
            userMapper.updateUser(user);
        }
        else{
            collection="["+mediaID+"]";
            user.setCollection(collection);
            userMapper.updateUser(user);
        }
    }

    @PostMapping("/cancelCollection")//取消收藏
    public void cancelCollection(int UID,int mediaID){
        User user=userMapper.getUserById(UID);
        String collection=user.getCollection();
        if(collection.substring(1,collection.length()-1).length()==1) {
            user.setCollection(null);
            userMapper.deleteCollection(user);
        }
        else{
            String[] new_collection=collection.substring(1,collection.length()-1).split(",");
            List<String> new_collection_List= Arrays.stream(new_collection).collect(Collectors.toList());
            new_collection_List.remove(String.valueOf(mediaID));
            String[] result=new_collection_List.toArray(new String[0]);
            StringBuilder response=new StringBuilder("[");
            for(String str:result)
                response.append(str+",");
            String finally_response=response.substring(0,response.length()-1)+"]";
            user.setCollection(finally_response.toString());
            userMapper.deleteCollection(user);
        }
    }
}
