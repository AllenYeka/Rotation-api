package wrq.rotation.media;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("wrq.rotation.media.mapper")
@EnableFeignClients
public class MediaApplication {
    public static void main(String[] args){
        ApplicationContext context= SpringApplication.run(MediaApplication.class, args);
        System.out.println("杰洛");
    }
}
