package wrq.rotation.content.service;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;
import wrq.rotation.content.model.po.Comment;
import wrq.rotation.content.model.po.Tip;

import java.io.IOException;
import java.util.List;

public interface TipService {
    PageInfo tipList(Integer pageNo);
    Integer addTip(Tip tip, MultipartFile file) throws IOException;
    List<Comment> getComment(Integer tipId);
    int addComment(Comment comment);
}
