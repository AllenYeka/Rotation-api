package wrq.rotation.media.service;
import com.github.pagehelper.PageInfo;
import wrq.rotation.common.model.po.User;
import wrq.rotation.media.model.po.Media;

import java.util.List;
import java.util.Map;

public interface MediaService {
    int addMedia(Media media);
    PageInfo mediaList(Integer pageNo, String searchInfo);
    PageInfo allMedia(Integer pageNo);
    int mediaStatus(Integer mediaId,boolean status);
    List<Map> getUserMedia(Integer uid);
    List<Map> getUserCollection(Integer uid);
}
