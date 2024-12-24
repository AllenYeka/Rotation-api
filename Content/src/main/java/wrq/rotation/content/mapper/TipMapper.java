package wrq.rotation.content.mapper;
import wrq.rotation.content.model.po.Comment;
import wrq.rotation.content.model.po.Tip;
import java.util.List;

public interface TipMapper {
    List<Tip> tipList();
    int addTip(Tip tip);
    List<Comment> getComment(Integer tipId);
    int addComment(Comment comment);
}
