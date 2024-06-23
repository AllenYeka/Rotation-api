import com.alibaba.fastjson.JSON;
import io.minio.messages.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import wrq.rotation.MediaApplication;
import wrq.rotation.utils.MinioUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = MediaApplication.class)
public class MediaTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MinioUtil minioUtil;

    @Test
    public void test02(){

    }

    @Test
    public void test01(){
        List<Item> items=minioUtil.fileList();
        List<String> pictures=items.stream()
                .map((item)->{return minioUtil.prePicture(item.objectName());})
                .skip((1-1)*2)
                .limit(4)
                .collect(Collectors.toList());
        for(String i:pictures)
            System.out.println(i);
    }
}
