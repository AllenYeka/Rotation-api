package wrq.rotation.service;
import wrq.rotation.model.dto.UserMediaDto;
import wrq.rotation.model.po.Media;
import wrq.rotation.model.po.User;

import java.util.List;

public interface MediaService {
    int addMedia(Media media);
    List<Media> getAllMedia();
    List<Media> getMediaByUser(String username);
    int deleteMedia(int id);
    Media getMediaById(int id);
    UserMediaDto getCCF(User user);
}
