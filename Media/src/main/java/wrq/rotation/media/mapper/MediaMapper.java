package wrq.rotation.media.mapper;
import org.apache.ibatis.annotations.Param;
import wrq.rotation.media.model.po.Media;

import java.util.List;
import java.util.Map;

public interface MediaMapper {
    List<Media> allMedia();
    List<Media> mediaList(String searchInfo);
    int insertMedia(Media media);
    int mediaStatus(@Param("mediaId")Integer mediaId,@Param("status")boolean status);
    List<Map> queryMediaByUid(Integer uid);
    List<Map> queryCollectById(Integer uid);
}
