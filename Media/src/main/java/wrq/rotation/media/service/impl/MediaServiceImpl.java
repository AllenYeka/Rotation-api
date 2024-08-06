package wrq.rotation.media.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrq.rotation.media.mapper.MediaMapper;
import wrq.rotation.media.mapper.UserMapper;
import wrq.rotation.media.model.po.Media;
import wrq.rotation.media.model.po.User;
import wrq.rotation.media.service.MediaService;
import wrq.rotation.media.model.dto.UserMediaDto;

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
        if(user.getCollection()!=null&&!"".equals(user.getCollection())) {
            String[] collection = user.getCollection().substring(1, user.getCollection().length() - 1).split("[,]");
            userMediaDto.setCollection(mediaMapper.queryCollectionMedia(collection));
        }
        if(user.getConcern()!=null&&!"".equals(user.getConcern())) {
            String[] concern = user.getConcern().substring(1, user.getConcern().length() - 1).split("[,]");
            userMediaDto.setConcern(userMapper.queryConcern(concern));
        }
        if(user.getFans()!=null&&!"".equals(user.getFans())) {
            String[] fans = user.getFans().substring(1, user.getFans().length() - 1).split("[,]");
            userMediaDto.setFans(userMapper.queryFans(fans));
        }
        return userMediaDto;
    }
}
