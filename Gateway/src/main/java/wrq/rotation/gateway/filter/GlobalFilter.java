package wrq.rotation.gateway.filter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Authorization: "+exchange.getRequest().getHeaders().get("Authorization"));
        System.out.println("Qrole: "+exchange.getRequest().getHeaders().get("Qrole"));
        return chain.filter(exchange);
    }
}
