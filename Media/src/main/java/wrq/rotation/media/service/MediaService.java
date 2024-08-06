package wrq.rotation.media.service;
import wrq.rotation.media.model.po.Media;
import wrq.rotation.media.model.po.User;
import wrq.rotation.media.model.dto.UserMediaDto;

import java.util.List;

public interface MediaService {
    int addMedia(Media media);
    List<Media> getAllMedia();
    List<Media> getMediaByUser(String username);
    int deleteMedia(int id);
    Media getMediaById(int id);
    UserMediaDto getCCF(User user);
}
