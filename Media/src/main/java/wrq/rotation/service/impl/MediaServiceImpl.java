package wrq.rotation.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrq.rotation.mapper.MediaMapper;
import wrq.rotation.model.po.Media;
import wrq.rotation.service.MediaService;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaMapper mediaMapper;
    @Override
    public int addMedia(Media media) {
        return mediaMapper.insertMedia(media);
    }

    @Override
    public List<Media> getAllMedia() {
        return mediaMapper.queryAllMedia();
    }

    @Override
    public List<Media> getMediaByUser(String username) {
        return mediaMapper.queryMediaByUser(username);
    }

    @Override
    public int deleteMedia(int id) {
        return mediaMapper.deleteMedia(id);
    }

    @Override
    public Media getMediaById(int id) {
        return mediaMapper.queryMediaById(id);
    }
}
