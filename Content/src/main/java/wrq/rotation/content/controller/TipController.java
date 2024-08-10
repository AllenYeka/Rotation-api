package wrq.rotation.content.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wrq.rotation.content.config.MinioConfig;
import wrq.rotation.content.model.dto.TipDto;
import wrq.rotation.content.model.po.Role;
import wrq.rotation.content.model.po.Tip;
import wrq.rotation.content.model.pojo.Comment;
import wrq.rotation.content.service.TipService;
import wrq.rotation.content.util.MinioUtil;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/content")
public class TipController {
    @Autowired
    private TipService tipService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MinioUtil minioUtil;

    @GetMapping("/getAllSimpleTip")
    public List<Tip> getAllSimpleTip(){
        if(stringRedisTemplate.opsForValue().get("allSimpleTip")!=null){
            System.out.println("allSimpleTip"+"--->redis缓存");
            List<Tip> tips= JSON.parseArray(stringRedisTemplate.opsForValue().get("allSimpleTip"),Tip.class);
            System.out.println(tips);
            return tips;
        }
        else {
            List<Tip> tips=tipService.getAllSimpleTip();
            stringRedisTemplate.opsForValue().set("allSimpleTip",JSON.toJSONString(tips),Duration.ofMinutes(1));
            return tips;
        }
    }

    @GetMapping("/getTipById/{tipId}")
    public TipDto getAllSimpleTip(@PathVariable int tipId){
        return tipService.getTipById(tipId);
    }

    @PostMapping("/insertTip")
    public void insertTip(Tip newTip, MultipartFile file) throws IOException {
        Tip tip=new Tip();
        tip.setUsername(newTip.getUsername());
        tip.setTitle(newTip.getTitle());
        tip.setUid(newTip.getUid());
        tip.setDescription(newTip.getDescription());
        tip.setAvatarUrl(newTip.getAvatarUrl());
        tip.setTipPicture("http://127.0.0.1:9000/tip/"+file.getOriginalFilename());
        tipService.addTip(tip);
        stringRedisTemplate.delete("allSimpleTip");
        minioUtil.upload(file.getOriginalFilename(),file.getInputStream(),"tip");
    }


    @PostMapping("/sendComment")
    public Comment sendComment(int tipId,@RequestBody Comment comment) throws IOException {
        TipDto tipDto=tipService.getTipById(tipId);
        List<Comment> comments=tipDto.getComments();
        if(comments==null||"".equals(comments)||comments.size()==0) {
            comments = new ArrayList<>();
            comment.setCommentId(0);
            comment.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            comments.add(comment);
        }
        else{
            int newCommentId=comments.get(comments.size()-1).getCommentId()+1;
            comment.setCommentId(newCommentId);
            comment.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            comments.add(comment);
        }
        Tip tip=new Tip();
        BeanUtils.copyProperties(tipDto,tip);
        tip.setComments(JSON.toJSONString(comments));
        tipService.updateTip(tip);
        return comment;
    }
}