package wrq.rotation.content.mapper;
import wrq.rotation.content.model.po.Tip;
import java.util.List;

public interface TipMapper {
    List<Tip> queryAllTip();
    Tip queryTipById(int tipId);
    int addTip(Tip tip);
    int updateTip(Tip tip);
}
