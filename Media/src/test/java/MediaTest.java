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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        mediaService.addMedia(new Media(18,"1","2",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),"3","4"));
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
