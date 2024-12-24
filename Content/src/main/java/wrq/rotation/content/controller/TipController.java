package wrq.rotation.content.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.content.model.po.Comment;
import wrq.rotation.content.model.po.Tip;
import wrq.rotation.content.service.TipService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/content")
public class TipController {
    @Autowired
    private TipService tipService;
    @GetMapping("/tipList")
    public ResponseDTO tipList(Integer pageNo){
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(tipService.tipList(pageNo));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
        }
        return responseDTO;
    }

    @PostMapping("/addTip")
    public ResponseDTO addTip(Tip tip, @RequestParam("file")MultipartFile file) throws IOException {
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(tipService.addTip(tip,file));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
        }
        return responseDTO;
    }

    @GetMapping("/getComment")
    public ResponseDTO getComment(Integer tipId){
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(tipService.getComment(tipId));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
        }
        return responseDTO;
    }

    @PostMapping("/sendComment")
    public ResponseDTO sendComment(@RequestBody Comment comment){
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            responseDTO.setDataList(tipService.addComment(comment));
        }catch (RuntimeException e){
            responseDTO.setStatus(500);
            responseDTO.setMsg(e.toString());
        }
        return responseDTO;
    }
}
