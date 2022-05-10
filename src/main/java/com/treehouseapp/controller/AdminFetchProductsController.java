package com.treehouseapp.controller;

import com.treehouseapp.dto.AdminViewOrderDTO;
import com.treehouseapp.exception.ProductNotFoundException;
import com.treehouseapp.model.enums.MenuCategory;
import com.treehouseapp.model.flowers.MenuItem;
import com.treehouseapp.payload.UserFetchAllFlowersResponse;
import com.treehouseapp.repository.MenuItemRepository;
import com.treehouseapp.service.serviceImpl.OrderServiceImplementation;
import com.treehouseapp.service.serviceInterfaces.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminFetchProductsController {
    private final MenuItemService service;
    private final MenuItemRepository menuRepo;
    private final OrderServiceImplementation orderServiceImplementation;

    @Autowired
    public AdminFetchProductsController(MenuItemService service, MenuItemRepository repo,
                                        OrderServiceImplementation orderServiceImplementation) {
        this.service = service;
        menuRepo = repo;
        this.orderServiceImplementation = orderServiceImplementation;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<MenuItem> getProduct(@PathVariable(name = "productId") Long prodId){

       MenuItem item = menuRepo.findById(prodId).
               orElseThrow(()->
               new ProductNotFoundException("Product Not Found on Id: "+prodId));
        return ResponseEntity.ok(item);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductsByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size){

        UserFetchAllFlowersResponse mealsPage = service.fetchAllFlowers(page, "name");
        return ResponseEntity.ok(mealsPage);
    }

    @GetMapping("/products/category")
    public ResponseEntity<UserFetchAllFlowersResponse> getProductsByCategory(
            @RequestParam("category") MenuCategory category,
            @RequestParam(name = "pageNo") int page,
            @RequestParam(name = "pageSize") int size){

        UserFetchAllFlowersResponse mealResponse = service.fetchFlowersByCategory(category, page, size);
        return ResponseEntity.ok(mealResponse);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<AdminViewOrderDTO>> viewOrdersHistory() {
        return ResponseEntity.ok().body(orderServiceImplementation.fetchAllOrdersToAdminDashboard());
    }
}
