package wrq.rotation.content.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wrq.rotation.common.config.UserContext;
import wrq.rotation.common.model.dto.ResponseDTO;
import wrq.rotation.common.model.pojo.MyUserDetail;
import wrq.rotation.content.service.MenuService;
import wrq.rotation.content.model.dto.MenuItemDto;

import java.util.List;

@RestController
@RequestMapping("/content/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/menuList")
    public ResponseDTO menuItemList(){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setDataList(menuService.menuList(UserContext.getUserType()));
        return responseDTO;
    }
}
