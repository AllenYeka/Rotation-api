import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.minio.messages.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import wrq.rotation.media.MediaApplication;
import wrq.rotation.media.mapper.MediaMapper;
import wrq.rotation.media.model.po.Media;
import wrq.rotation.media.service.MediaService;
import wrq.rotation.media.utils.MinioUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(classes = MediaApplication.class)
public class MediaTest {
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MediaService mediaService;
    @Autowired
    private MediaMapper mediaMapper;
    @Test
    public void test01(){
        Page page=PageHelper.startPage(1,9);
        mediaMapper.allMedia();
        PageInfo mediaList=new PageInfo(page.getResult());//返回前端list、total等
    }
}
