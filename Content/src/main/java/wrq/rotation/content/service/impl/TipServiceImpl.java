package wrq.rotation.content.service.impl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrq.rotation.content.mapper.TipMapper;
import wrq.rotation.content.model.dto.TipDto;
import wrq.rotation.content.model.po.Tip;
import wrq.rotation.content.model.pojo.Comment;
import wrq.rotation.content.service.TipService;

import java.util.List;

@Service
public class TipServiceImpl implements TipService {
    @Autowired
    private TipMapper tipMapper;

    @Override
    public TipDto getTipById(int id) {
        Tip tip=tipMapper.queryTipById(id);
        TipDto tipDto=new TipDto();
        BeanUtils.copyProperties(tip,tipDto);
        List<Comment> comments= JSON.parseArray(tip.getComments(), Comment.class);
        tipDto.setComments(comments);
        return tipDto;
    }

    @Override
    public List<Tip> getAllSimpleTip() {
        return tipMapper.queryAllTip();
    }

    @Override
    public int addTip(Tip tip) {
        return tipMapper.addTip(tip);
    }

    @Override
    public int updateTip(Tip tip) {
        return tipMapper.updateTip(tip);
    }
}
