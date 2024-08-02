import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.minio.messages.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import wrq.rotation.MediaApplication;
import wrq.rotation.mapper.MediaMapper;
import wrq.rotation.mapper.UserMapper;
import wrq.rotation.model.po.Media;
import wrq.rotation.model.po.User;
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
    @Autowired
    private MediaMapper mediaMapper;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test02(){
        User user=userMapper.queryUser("jojo");
        String[] concern=user.getConcern().substring(1,user.getConcern().length()-1).split("[,]");
        userMapper.queryConcern(concern);

        String[] fans=user.getFans().substring(1,user.getFans().length()-1).split("[,]");
        userMapper.queryFans(fans);
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
