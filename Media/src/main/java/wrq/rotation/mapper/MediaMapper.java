package wrq.rotation.mapper;
import org.apache.ibatis.annotations.Param;
import wrq.rotation.model.po.Media;
import wrq.rotation.model.po.User;

import java.util.List;

public interface MediaMapper {
    int insertMedia(Media media);
    List<Media> queryAllMedia();
    List<Media> queryMediaByUser(String username);
    int deleteMedia(int id);
    Media queryMediaById(int id);
    List<Media> queryCollectionMedia(@Param("collection") String[] collection);
}
