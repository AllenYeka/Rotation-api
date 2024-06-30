package wrq.rotation.mapper;
import wrq.rotation.model.po.Media;
import java.util.List;

public interface MediaMapper {
    int insertMedia(Media media);
    List<Media> queryAllMedia();
    List<Media> queryMediaByUser(String username);
    int deleteMedia(int id);
    Media queryMediaById(int id);
}
