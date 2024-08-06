import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wrq.rotation.gateway.GatewayApplication;
import wrq.rotation.gateway.mapper.UserMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = GatewayApplication.class)
public class GatewayTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        int targetUID=3;
        String a="[1,3,4,5]";
        String[] as=a.substring(1,a.length()-1).split(",");
        List<String> sd= Arrays.stream(as).collect(Collectors.toList());
        sd.remove(String.valueOf(targetUID));
        for(String s:sd)
            System.out.println(s);
        String[]xc=(String[]) sd.toArray();
        for(String s:xc)
            System.out.println(s);
    }
}
