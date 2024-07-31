package wrq.rotation.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private ReactiveUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailHandler failHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){//编码器
        return new BCryptPasswordEncoder();
    }

    /*
        (1)提供给前端的接口是/login(不需要自定义登录接口)
        (2)前端ajax请求的请求头中Content-Type:application/x-www-form-urlencoded
    */

    @Bean
    protected SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange()//链式配置url的保护规则
                .anyExchange().permitAll();//所有请求都不需要认证
        http.formLogin()//提供/login接口
                .authenticationSuccessHandler(successHandler)
                .authenticationFailureHandler(failHandler);
        http.logout().logoutUrl("/logout");
        http.csrf().disable();
        return http.build();
    }

}
