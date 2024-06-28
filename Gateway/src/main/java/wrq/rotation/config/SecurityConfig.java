package wrq.rotation.config;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;
import wrq.rotation.model.dto.ResponseData;
import wrq.rotation.utils.JWTUtil;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
                .pathMatchers("/login","/oauth/**","/getAccessToken").permitAll()
                .pathMatchers("/content/**","/media/**").permitAll()//不需要认证直接访问
                .anyExchange().authenticated();//(除了以上之外)所有请求需要认证
        http.formLogin()//提供login接口
                .authenticationSuccessHandler(successHandler)
                .authenticationFailureHandler(failHandler);
        http.oauth2Client().and().oauth2Login();
        http.logout().logoutUrl("/logout");
        http.csrf().disable();
        return http.build();
    }

}
