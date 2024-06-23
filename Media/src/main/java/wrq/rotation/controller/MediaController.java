package wrq.rotation.controller;
import com.alibaba.fastjson.JSON;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wrq.rotation.model.dto.Picture;
import wrq.rotation.utils.MinioUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/media")
public class MediaController {
    public static int PAGE_SIZE=9;
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/getPictureByPageNo/{pageNo}")
    public List<Picture> getAllPicture(@PathVariable int pageNo){
        List<Picture> pictures=null;
        if(stringRedisTemplate.opsForValue().get("pictures"+pageNo)!=null) {
            pictures=JSON.parseArray(stringRedisTemplate.opsForValue().get("pictures"+pageNo),Picture.class);
            System.out.println("走redis缓存"+pictures);
        }
        else{
            List<Item> items=minioUtil.fileList();
            pictures=items.stream()
                    .map((item)->{return new Picture(item.objectName(),minioUtil.prePicture(item.objectName()));})
                    .skip((pageNo-1)*PAGE_SIZE)
                    .limit(PAGE_SIZE)
                    .collect(Collectors.toList());
            stringRedisTemplate.opsForValue().set("pictures"+pageNo,JSON.toJSONString(pictures),Duration.ofMinutes(1));
            System.out.println("走文件系统"+pictures);
        }
        return pictures;
    }

    @GetMapping("/getPictureCount")
    public int getPictureCount(){
        return minioUtil.fileList().size();
    }

    @PostMapping("/uploadPicture")
    public void uploadPicture(@RequestParam("file") List<MultipartFile> fileList) throws IOException {
        System.out.println(fileList.get(0).getOriginalFilename());
        for(MultipartFile file:fileList)
            minioUtil.upload(file.getOriginalFilename(),file.getInputStream());
    }

    @GetMapping("/deletePicture")
    public boolean getPictureCount(String objectName,int pageNo){
        stringRedisTemplate.delete("pictures"+pageNo);
        return minioUtil.removeFile(objectName);
    }
}
