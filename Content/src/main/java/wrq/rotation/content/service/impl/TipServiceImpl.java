package wrq.rotation.content.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wrq.rotation.content.mapper.TipMapper;
import wrq.rotation.content.model.po.Comment;
import wrq.rotation.content.model.po.Tip;
import wrq.rotation.content.service.TipService;
import wrq.rotation.content.util.MinioUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipServiceImpl implements TipService {
    private final static Integer PAGE_SIZE=5;
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private TipMapper tipMapper;
    @Override
    public PageInfo tipList(Integer pageNo) {
        Page page= PageHelper.startPage(pageNo,PAGE_SIZE);
        tipMapper.tipList();
        PageInfo tipList=new PageInfo(page.getResult());//返回前端list、total等
        return tipList;
    }

    @Override
    public Integer addTip(Tip tip, MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        String objectName=fileName.substring(fileName.lastIndexOf("\\")+1);
        List<String> fileList=minioUtil.fileList("tip").stream().map((item)->{return item.objectName();}).collect(Collectors.toList());
        if(!fileList.contains(objectName))
            minioUtil.upload(objectName,file.getInputStream(),"tip");
        tipMapper.addTip(tip);
        return tip.getId();
    }

    @Override
    public List<Comment> getComment(Integer tipId) {
        return tipMapper.getComment(tipId);
    }

    @Override
    public int addComment(Comment comment) {
        return tipMapper.addComment(comment);
    }
}
