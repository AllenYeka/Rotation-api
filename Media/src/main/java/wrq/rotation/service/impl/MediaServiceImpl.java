package wrq.rotation.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrq.rotation.mapper.MediaMapper;
import wrq.rotation.mapper.UserMapper;
import wrq.rotation.model.dto.UserMediaDto;
import wrq.rotation.model.po.Media;
import wrq.rotation.model.po.User;
import wrq.rotation.service.MediaService;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private UserMapper userMapper;
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
    @Override
    public UserMediaDto getCCF(User user) {
        UserMediaDto userMediaDto=new UserMediaDto();
        userMediaDto.setUser(user);
        userMediaDto.setMedia(mediaMapper.queryMediaByUser(user.getUsername()));
        if(user.getCollection()!=null) {
            String[] collection = user.getCollection().substring(1, user.getCollection().length() - 1).split("[,]");
            userMediaDto.setCollection(mediaMapper.queryCollectionMedia(collection));
        }
        if(user.getConcern()!=null) {
            String[] concern = user.getConcern().substring(1, user.getConcern().length() - 1).split("[,]");
            userMediaDto.setConcern(userMapper.queryConcern(concern));
        }
        if(user.getFans()!=null) {
            String[] fans = user.getFans().substring(1, user.getFans().length() - 1).split("[,]");
            userMediaDto.setFans(userMapper.queryFans(fans));
        }
        return userMediaDto;
    }
}
