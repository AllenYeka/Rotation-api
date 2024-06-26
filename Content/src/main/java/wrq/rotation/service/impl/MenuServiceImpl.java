package wrq.rotation.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrq.rotation.mapper.MenuMapper;
import wrq.rotation.model.dto.MenuItemDto;
import wrq.rotation.model.po.MenuItem;
import wrq.rotation.service.MenuService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    private List<MenuItemDto> subMenu(MenuItem parent,List<MenuItem> menuItem){
        if(parent.getIsLeaf()==1)
            return null;
        List<MenuItemDto> subMenu=new ArrayList<>();
        for(MenuItem menu:menuItem){
            if(menu.getParentId()==parent.getId()) {
                MenuItemDto menuItemDto=new MenuItemDto(menu,subMenu(menu,menuItem));
                subMenu.add(menuItemDto);
            }
        }
        return subMenu;
    }
    @Override
    public List<MenuItemDto> menuList() {
        List<MenuItemDto> result=new ArrayList<>();
        List<MenuItem> menuItemList=menuMapper.menuList();
        for(int i=0;i<menuItemList.size();i++){
            if(menuItemList.get(i).getParentId()==0){
                MenuItemDto menuItemDto=new MenuItemDto(menuItemList.get(i),subMenu(menuItemList.get(i),menuItemList));
                result.add(menuItemDto);
            }
        }
        return result;
    }
}
