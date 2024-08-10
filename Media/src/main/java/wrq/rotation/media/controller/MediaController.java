package wrq.rotation.media.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wrq.rotation.media.model.po.Media;
import wrq.rotation.media.model.po.User;
import wrq.rotation.media.utils.MinioUtil;
import wrq.rotation.media.client.GatewayClient;
import wrq.rotation.media.model.dto.UserMediaDto;
import wrq.rotation.media.service.MediaService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {
    public static int PAGE_SIZE=9;
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MediaService mediaService;
    @Autowired
    private GatewayClient gatewayClient;

    @GetMapping("/getPictureByPageNo/{pageNo}")
    public List<Media> getAllMedia(@PathVariable int pageNo){
        List<Media> medias=null;
        if(stringRedisTemplate.opsForValue().get("medias"+pageNo)!=null) {
            medias=JSON.parseArray(stringRedisTemplate.opsForValue().get("medias"+pageNo),Media.class);
            System.out.println("走redis缓存"+medias);
        }
        else{
            PageHelper.startPage(pageNo,PAGE_SIZE);
            medias=mediaService.getAllMedia();
            System.out.println("走数据库"+medias);
            stringRedisTemplate.opsForValue().set("medias"+pageNo,JSON.toJSONString(medias),Duration.ofMinutes(1));
        }
        return medias;
    }

    @GetMapping("/getPictureCount")
    public int getPictureCount(){
        return minioUtil.fileList("wrq").size();
    }

    @PostMapping("/uploadPicture")
    public void uploadPicture(@RequestParam("file") List<MultipartFile> fileList, HttpServletRequest request) throws IOException {
        String username= URLDecoder.decode(request.getHeader("RUser"),"UTF-8");
        String userAvatarUrl= request.getHeader("Ravatar");
        String RPageNo= request.getHeader("RPageNo");
        Media media=new Media(username,userAvatarUrl);
        for(MultipartFile file:fileList) {
            String fileName=file.getOriginalFilename();
            String objectName=fileName.substring(fileName.lastIndexOf("\\")+1);
            media.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            media.setObjectName(objectName);
            minioUtil.upload(fileName, file.getInputStream(),"wrq");
            media.setObjectUrl(minioUtil.prePicture(objectName,"wrq").split("\\?")[0]);
            mediaService.addMedia(media);
        }
        stringRedisTemplate.delete("medias"+RPageNo);
    }

    @GetMapping("/deletePicture")
    public boolean deletePicture(int pictureId,int pageNo){
        stringRedisTemplate.delete("medias"+pageNo);
        String objectName=mediaService.getMediaById(pictureId).getObjectName();
        mediaService.deleteMedia(pictureId);
        return minioUtil.removeFile(objectName,"wrq");
    }

    @GetMapping("/getMediaByUser")
    public List<Media>  getMediaByUser(String username){
        return mediaService.getMediaByUser(username);
    }

    @GetMapping("/getUserMediaByName")
    public UserMediaDto getUserMediaById(String username){
        UserMediaDto userMediaDto=new UserMediaDto();
        User user= gatewayClient.getUserByName(username);
        return mediaService.getCCF(user);
    }

}
