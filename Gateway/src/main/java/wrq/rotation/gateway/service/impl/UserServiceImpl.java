package wrq.rotation.gateway.service.impl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import wrq.rotation.common.model.po.User;
import wrq.rotation.common.model.pojo.MyUserDetail;
import wrq.rotation.gateway.mapper.UserMapper;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements ReactiveUserDetailsService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        User user=userMapper.queryUserByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("用户不存在!");
        Mono<UserDetails> userDetail=Mono.just(new MyUserDetail(user,null));
        return userDetail;
    }
    public Map<String,List<Integer>> getIdList(Integer uid){
        List<Integer> myCollect=userMapper.queryCollectId(uid);
        List<Integer> myConcern=userMapper.queryConcernId(uid);
        Map<String,List<Integer>> dataList=new HashMap<>();
        dataList.put("myCollect",myCollect);
        dataList.put("myConcern",myConcern);
        return dataList;
    }
    public int updateConcern(Integer uid, Integer concernId,boolean deleted){
        if(deleted)
            return userMapper.cancelConcern(uid, concernId);
        else
            return userMapper.concern(uid, concernId);
    }

    public int updateCollect(Integer uid, Integer mediaId,boolean deleted){
        if(deleted)
            return userMapper.cancelCollect(uid, mediaId);
        else
            return userMapper.collect(uid, mediaId);
    }
    public User getUser(Integer uid){
        return userMapper.queryUser(uid);
    }
    public boolean existUser(Integer uid){
        return userMapper.existUser(uid)!=null?true:false;
    }
    public List<Map> getAllUser(){
        return userMapper.queryAllUser();
    }
    public int registerOauthUser(User user){
        return userMapper.insertOauthUser(user);
    }
    public List<Map> getUserConcern(Integer uid){
        return userMapper.queryConcernById(uid);
    }
    public List<Map> getUserFans(Integer uid){
        return userMapper.queryFanById(uid);
    }
}
