package wrq.rotation.gateway.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import wrq.rotation.gateway.model.pojo.MyUserDetail;
import wrq.rotation.gateway.mapper.UserMapper;
import wrq.rotation.gateway.model.po.User;

import java.util.Collection;

@Service
public class UserServiceImpl implements ReactiveUserDetailsService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        User user=userMapper.getUserByName(username);
        if(user==null)
            throw new UsernameNotFoundException("用户不存在!");
        Collection<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ZONGTONG,总统大权");//权限和角色
        Mono<UserDetails> userDetail=Mono.just(new MyUserDetail(user,auths));
        return userDetail;
    }
}
