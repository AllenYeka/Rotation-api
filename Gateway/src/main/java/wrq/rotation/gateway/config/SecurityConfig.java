package wrq.rotation.gateway.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailHandler failHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){//编码器
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange()//链式配置url的保护规则
                .anyExchange().permitAll();//所有请求都不需要认证
        http.formLogin()//提供/login接口 ( 且前端ajax请求头中Content-Type必须为application/x-www-form-urlencoded )
                .authenticationSuccessHandler(successHandler)
                .authenticationFailureHandler(failHandler);
        http.logout().logoutUrl("/logout");
        http.csrf().disable();
        return http.build();
    }

}
