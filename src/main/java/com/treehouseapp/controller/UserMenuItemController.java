package com.treehouseapp.controller;

import com.treehouseapp.model.enums.MenuCategory;
import com.treehouseapp.payload.UserFetchAllFlowersResponse;
import com.treehouseapp.service.serviceInterfaces.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class UserMenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping("/{pageNo}/{sortBy}")
    public ResponseEntity<?> fetchAllProducts(@PathVariable Integer pageNo, @PathVariable String sortBy){
        UserFetchAllFlowersResponse userFetchAllMealsResponse = menuItemService.fetchAllFlowers(pageNo, sortBy);
        return ResponseEntity.ok(userFetchAllMealsResponse);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<?> fetchOneProduct(@PathVariable(name = "menuId") Long menuId){
        return ResponseEntity.ok(menuItemService.getMenuItemById(menuId));
    }

    @GetMapping("/search")
    public ResponseEntity<UserFetchAllFlowersResponse> fetchByKeyword(
            @RequestParam("searchKey") String keyword,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize) {
        UserFetchAllFlowersResponse mealsResponse = menuItemService.fetchFlowersByKeyWord(keyword, pageNo, pageSize);
        return ResponseEntity.ok(mealsResponse);
    }

    @GetMapping("/category")
    public ResponseEntity<UserFetchAllFlowersResponse> fetchProductsByCategory(
            @RequestParam("category") MenuCategory category,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize) {
        UserFetchAllFlowersResponse userFetchAllMealsResponse = menuItemService.fetchFlowersByCategory(category, pageNo, pageSize);
        return ResponseEntity.ok(userFetchAllMealsResponse);
    }
}