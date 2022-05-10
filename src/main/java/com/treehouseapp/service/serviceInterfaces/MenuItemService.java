package com.treehouseapp.service.serviceInterfaces;

import java.io.IOException;
import java.util.List;
//import java.util.Optional;
import com.treehouseapp.dto.MenuItemDto;
import com.treehouseapp.model.enums.MenuCategory;
import com.treehouseapp.model.flowers.MenuItem;
import com.treehouseapp.payload.MenuResponse;
import com.treehouseapp.payload.UserFetchAllFlowersResponse;


public interface MenuItemService {

    MenuResponse updateMenuItem(Long id, MenuItemDto menuItemDto) throws IOException;
    MenuItem getMenuItemById(Long id);
    List<MenuItem> getAllMenuItems();
    void deleteMenuItemById(Long id);
    UserFetchAllFlowersResponse fetchAllFlowers(Integer pageNo, String sortBy);
    UserFetchAllFlowersResponse fetchFlowersByKeyWord(String keyword, int pageNo, int pageSize);
    UserFetchAllFlowersResponse fetchFlowersByCategory(MenuCategory category, int pageNo, int pageSize);
    MenuResponse addMenuItem(MenuItemDto menuItemDto) throws IOException;






}
