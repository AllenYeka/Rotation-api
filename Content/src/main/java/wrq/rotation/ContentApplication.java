package wrq.rotation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("wrq.rotation.mapper")//扫描并注入mapper
public class ContentApplication {
    public static void main(String [] args){
        ApplicationContext context= SpringApplication.run(ContentApplication.class,args);
        System.out.println("胡桃");
    }
}
