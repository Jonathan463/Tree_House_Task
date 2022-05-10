package com.treehouseapp.controller;


import com.treehouseapp.model.flowers.MenuItem;
import com.treehouseapp.payload.FavouriteFlowerResponse;
import com.treehouseapp.service.serviceInterfaces.FavoriteFlowerService;
import com.treehouseapp.service.serviceInterfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class FavoriteController {

    private final FavoriteFlowerService favoriteMealService;
    private final UserServiceInterface userService;
    @PostMapping("/addfavoriteflower/{menuId}")
    public ResponseEntity<MenuItem> createFavoriteFlower( @PathVariable Long menuId){


        final MenuItem menuItem = favoriteMealService.createFavoriteFlower(userService.getUserIDFromSecurityContext(), menuId);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PostMapping("/deletefavoriteflower/{menuId}")
    public ResponseEntity<String> deleteFavoriteFlower( @PathVariable Long menuId){
        favoriteMealService.removeFromFlower(userService.getUserIDFromSecurityContext(), menuId);
        return new ResponseEntity<>("favorite meal with " + menuId + " has been removed from favorite.", HttpStatus.OK);
    }



    @GetMapping("/allfavoriteflowers")
    public ResponseEntity<FavouriteFlowerResponse> getAllFavoriteFlowersByAUser() {
        List<MenuItem> favoriteMeals = favoriteMealService.getAllFavoriteFlowersByAUser(userService.getUserIDFromSecurityContext());
        log.info(">>>>><<<<"+ favoriteMeals);
        FavouriteFlowerResponse response = new FavouriteFlowerResponse();
        response.setListOffavoriteFlowers(favoriteMeals);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getparticularfavoriteflowers/{menuId}")
    public ResponseEntity<MenuItem> getParticularFavoriteFlower(@PathVariable Long menuId){
        return ResponseEntity.ok(favoriteMealService.getAParticularFavoriteFlower(userService.getUserIDFromSecurityContext(), menuId));
    }

}