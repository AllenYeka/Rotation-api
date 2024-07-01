import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.minio.messages.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import wrq.rotation.MediaApplication;
import wrq.rotation.model.po.Media;
import wrq.rotation.service.MediaService;
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
    @Autowired
    private MediaService mediaService;
    @Test
    public void test02(){
        String url="http://127.0.0.1:9000/wrq/%E7%99%BD%E8%89%B2.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=wrq%2F20240630%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240630T062655Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=2e3487f93a7b11af261c58801b771fa1a928fb0841cf891afc37a7abf18a72e0";
        System.out.println(url.split("\\?")[0]);
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
