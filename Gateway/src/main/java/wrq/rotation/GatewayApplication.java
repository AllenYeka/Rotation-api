package wrq.rotation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("wrq.rotation.mapper")//扫描并注入mapper
public class GatewayApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext= SpringApplication.run(GatewayApplication.class, args);
        System.out.println("乔尼");
    }
}
