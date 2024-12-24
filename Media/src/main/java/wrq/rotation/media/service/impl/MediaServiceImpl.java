package wrq.rotation.media.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrq.rotation.media.mapper.MediaMapper;
import wrq.rotation.media.model.po.Media;
import wrq.rotation.media.service.MediaService;

import java.util.List;
import java.util.Map;

@Service
public class MediaServiceImpl implements MediaService {
    public static int PAGE_SIZE=12;
    @Autowired
    private MediaMapper mediaMapper;
    @Override
    public int addMedia(Media media) {
        return mediaMapper.insertMedia(media);
    }
    @Override
    public PageInfo mediaList(Integer pageNo,String searchInfo) {
        Page page=PageHelper.startPage(pageNo,PAGE_SIZE);
        mediaMapper.mediaList(searchInfo);
        PageInfo mediaList=new PageInfo(page.getResult());//返回前端list、total等
        return mediaList;
    }
    @Override
    public PageInfo allMedia(Integer pageNo) {
        Page page=PageHelper.startPage(pageNo,9);
        mediaMapper.allMedia();
        PageInfo mediaList=new PageInfo(page.getResult());//返回前端list、total等
        return mediaList;
    }
    @Override
    public int mediaStatus(Integer mediaId,boolean status) {
        return mediaMapper.mediaStatus(mediaId,status);
    }
    @Override
    public List<Map> getUserMedia(Integer uid) {
        return mediaMapper.queryMediaByUid(uid);
    }
    public List<Map> getUserCollection(Integer uid){
        return mediaMapper.queryCollectById(uid);
    }

}
