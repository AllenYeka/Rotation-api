package wrq.rotation.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import wrq.rotation.mapper.UserMapper;
import wrq.rotation.model.po.User;
import wrq.rotation.model.pojo.MyUserDetails;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//根据请求中的用户名获取用户信息
        User user = usersMapper.queryUser(username);
        if (user == null)
            throw new UsernameNotFoundException("用户名不存在!");
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));//加密
        Collection<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ZONGTONG,总统大权");//权限
        MyUserDetails userDetails = new MyUserDetails(user, auths);
        return userDetails;
    }
}