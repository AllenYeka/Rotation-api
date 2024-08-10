package wrq.rotation.content.service;
import wrq.rotation.content.model.dto.TipDto;
import wrq.rotation.content.model.po.Tip;
import java.util.List;

public interface TipService {
    TipDto getTipById(int id);
    List<Tip> getAllSimpleTip();
    int addTip(Tip tip);
    int updateTip(Tip tip);
}
