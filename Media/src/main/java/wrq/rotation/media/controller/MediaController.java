package wrq.rotation.media.controller;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wrq.rotation.common.config.UserContext;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.media.mapper.MediaMapper;
import wrq.rotation.media.model.po.Media;
import wrq.rotation.media.utils.MinioUtil;
import wrq.rotation.media.client.GatewayClient;
import wrq.rotation.media.service.MediaService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/media")
public class MediaController {
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MediaService mediaService;
    @Autowired
    private GatewayClient gatewayClient;
    @Autowired
    private MediaMapper mediaMapper;
    @GetMapping("/mediaList")
    public ResponseDTO mediaList(Integer pageNo,String searchInfo){//用户端
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(mediaService.mediaList(pageNo,searchInfo));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @GetMapping("/allMedia")
    public ResponseDTO allMedia(Integer pageNo){//后台管理端
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(mediaService.allMedia(pageNo));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @GetMapping("/getUserMedia")
    public ResponseDTO getUserMedia(){//获取用户的作品
        Integer uid= UserContext.getUserId();
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(mediaService.getUserMedia(uid));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @GetMapping("/getUserCollection")
    public ResponseDTO getUserCollection(){//收藏列表
        Integer uid= UserContext.getUserId();
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(mediaService.getUserCollection(uid));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @GetMapping("/getCreatorInfo")
    public ResponseDTO getCreatorInfo(Integer creatorId){//获取创作者所有信息
        ResponseDTO responseDTO=gatewayClient.getCreatorBaseInfoAndConcern(creatorId);
        try{
            Map<String,Object> dataList=(Map<String,Object>)responseDTO.getDataList();
            dataList.put("mediaList",mediaService.getUserMedia(creatorId));
            responseDTO.setDataList(dataList);
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return  responseDTO;
        }
        return responseDTO;
    }

    @PostMapping("/uploadPicture")
    public void uploadPicture(@RequestParam("file") List<MultipartFile> fileList) throws IOException {
        for(MultipartFile file:fileList) {
            Media media=new Media();
            String fileName=file.getOriginalFilename();
            String objectName=fileName.substring(fileName.lastIndexOf("\\")+1);
            media.setCreator(UserContext.getUserId());
            media.setObjectName(objectName);
            media.setStockingType(4);
            minioUtil.upload(fileName, file.getInputStream(),"wrq");
            media.setObjectUrl("http://127.0.0.1:9000/wrq/"+objectName);
            mediaService.addMedia(media);
        }
    }

    @PostMapping("/mediaStatus")
    public ResponseDTO deleteMedia(@RequestBody Map<String,Object> body){
        Integer mediaId=(Integer) body.get("mediaId");
        boolean status=(boolean)body.get("status");
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            mediaService.mediaStatus(mediaId,status);
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
            return responseDTO;
        }
        return responseDTO;
    }
}
