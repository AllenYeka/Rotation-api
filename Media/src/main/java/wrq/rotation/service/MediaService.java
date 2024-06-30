package wrq.rotation.service;
import wrq.rotation.model.po.Media;
import java.util.List;

public interface MediaService {
    int addMedia(Media media);
    List<Media> getAllMedia();
    List<Media> getMediaByUser(String username);
    int deleteMedia(int id);
    Media getMediaById(int id);
}
