package com.treehouseapp.service.serviceImpl;

import com.treehouseapp.dto.MenuItemDto;
import com.treehouseapp.exception.BadRequestException;
import com.treehouseapp.model.enums.MenuCategory;
import com.treehouseapp.payload.MenuResponse;
import com.treehouseapp.payload.UserFetchAllFlowersResponse;
import com.treehouseapp.repository.MenuItemRepository;
import com.treehouseapp.service.serviceInterfaces.ImageService;
import com.treehouseapp.service.serviceInterfaces.MenuItemService;
import com.treehouseapp.exception.MenuNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.treehouseapp.model.flowers.MenuItem;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class MenuServiceImplementation implements MenuItemService {

    private final MenuItemRepository menuItemRepository;



    public MenuServiceImplementation(MenuItemRepository menuItemRepository, ImageService imageService) {
        this.menuItemRepository = menuItemRepository;

    }


    @Override
    public MenuResponse addMenuItem(MenuItemDto menuItemDto) throws IOException {
        if(menuItemRepository.existsByName(menuItemDto.getName())){
            throw new BadRequestException("Menu already exists");
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.getName());
        menuItem.setCategory(menuItemDto.getCategory());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setDescription(menuItemDto.getDescription());
        menuItem.setImage(menuItemDto.getImage());
        MenuItem savedmenu = menuItemRepository.save(menuItem);
        return new MenuResponse("Menu item has been added", menuItem.getName(), menuItem.getPrice(),
                menuItem.getDescription(), menuItem.getCategory().name(), savedmenu.getImage());
    }

    @Override
    public MenuResponse updateMenuItem(Long id, MenuItemDto menuItemDto) throws IOException {

        MenuItem menuItem = getMenuItemById(id);
        if(menuItem == null){
            throw new BadRequestException("menu not found");
        }
        menuItem.setName(menuItemDto.getName());
        menuItem.setDescription(menuItemDto.getDescription());
        menuItem.setCategory(menuItemDto.getCategory());
        menuItem.setImage(menuItemDto.getImage());
        menuItem.setPrice(menuItemDto.getPrice());
        MenuItem updatedMenuitem = menuItemRepository.save(menuItem);
        return new MenuResponse("Menu item has been updated", menuItem.getName(), menuItem.getPrice(),
                menuItem.getDescription(), menuItem.getCategory().name(),updatedMenuitem.getImage());
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findMenuItemById(id).orElseThrow(()->new BadRequestException("Item " +  id + " was not found"));
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteMenuItemById(Long id) {
        var menuItem1 = menuItemRepository.findById(id);
        if(menuItem1.isEmpty()){
            throw new MenuNotFoundException("menu not found");
        }
        menuItemRepository.deleteById(id);
    }
    @Override
    public UserFetchAllFlowersResponse fetchAllFlowers(Integer pageNo, String sortBy) {
       int pageSize = getAllMenuItems().size();
        return getUserFetchAllFlowersResponse(pageNo, pageSize, sortBy, menuItemRepository);
    }

    private UserFetchAllFlowersResponse getUserFetchAllFlowersResponse(Integer pageNo, Integer pageSize, String sortBy, MenuItemRepository menuItemRepository) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<MenuItem> menuItems = menuItemRepository.findAll(paging);
        if(!menuItems.hasContent()) return new UserFetchAllFlowersResponse("No menu Item", menuItems.getContent());
        return new UserFetchAllFlowersResponse("Success", menuItems.getContent());
    }

    @Override
    public UserFetchAllFlowersResponse fetchFlowersByKeyWord(String keyword, int pageNo, int pageSize) {
        Page<MenuItem> menuItems = menuItemRepository.findAllByNameContains(keyword, PageRequest.of(pageNo, pageSize));
        if (!menuItems.hasContent()) return new UserFetchAllFlowersResponse("No match found", menuItems.getContent());
        return new UserFetchAllFlowersResponse("Match found", menuItems.getContent());
    }

    @Override
    public UserFetchAllFlowersResponse fetchFlowersByCategory(MenuCategory category, int pageNo, int pageSize) {
        Page<MenuItem> menuItems = menuItemRepository.findAllByCategory(category, PageRequest.of(pageNo, pageSize));
        if (!menuItems.hasContent()) return new UserFetchAllFlowersResponse("Category does not exist or No items in Category", menuItems.getContent());
        return new UserFetchAllFlowersResponse("Success", menuItems.getContent());
    }
}

