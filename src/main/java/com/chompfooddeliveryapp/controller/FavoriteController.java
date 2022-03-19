package com.chompfooddeliveryapp.controller;


import com.chompfooddeliveryapp.model.meals.FavoriteMeal;
import com.chompfooddeliveryapp.model.meals.MenuItem;
import com.chompfooddeliveryapp.payload.FavouriteMealResponse;
import com.chompfooddeliveryapp.service.serviceImpl.UserServiceImpl;
import com.chompfooddeliveryapp.service.serviceInterfaces.FavoriteMealService;
import com.chompfooddeliveryapp.service.serviceInterfaces.UserServiceInterface;
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

    private final FavoriteMealService favoriteMealService;
    private final UserServiceInterface userService;
    @PostMapping("/addfavoritemeal/{menuId}")
    public ResponseEntity<MenuItem> createFavoriteMeal( @PathVariable Long menuId){


        final MenuItem menuItem = favoriteMealService.createFavoriteMeal(userService.getUserIDFromSecurityContext(), menuId);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PostMapping("/deletefavoritemeal/{menuId}")
    public ResponseEntity<String> deleteFavoriteMeal( @PathVariable Long menuId){
        favoriteMealService.removeFromFavoriteMeal(userService.getUserIDFromSecurityContext(), menuId);
        return new ResponseEntity<>("favorite meal with " + menuId + " has been removed from favorite.", HttpStatus.OK);
    }

//    @GetMapping("/allfavoritemeals")
//    public ResponseEntity<List<MenuItem>> getAllFavoriteMealsByAUser() {
//        List<FavoriteMeal> favoriteMeals = favoriteMealService.getAllFavoriteMealsByAUser(userService.getUserIDFromSecurityContext());
//        log.info(">>>>><<<<"+ favoriteMeals);
//        List<MenuItem> itemsList = favoriteMeals.stream()
//                .map(favoriteMeal -> favoriteMeal.getMenuid())
//                .collect(Collectors.toList());
//        FavouriteMealResponse response = new FavouriteMealResponse(itemsList);
////        setFavoriteMealList(favoriteMeals);
//        System.out.println("Returning all favorites to controller method");
//        return ResponseEntity.ok(itemsList);
//    }

    @GetMapping("/allfavoritemeals")
    public ResponseEntity<FavouriteMealResponse> getAllFavoriteMealsByAUser() {
        List<MenuItem> favoriteMeals = favoriteMealService.getAllFavoriteMealsByAUser(userService.getUserIDFromSecurityContext());
        log.info(">>>>><<<<"+ favoriteMeals);
        FavouriteMealResponse response = new FavouriteMealResponse();
        response.setListOffavoriteMeals(favoriteMeals);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getparticularfavoritemeal/{menuId}")
    public ResponseEntity<MenuItem> getParticularFavoriteMeal(@PathVariable Long menuId){
        return ResponseEntity.ok(favoriteMealService.getAParticularFavoriteMeal(userService.getUserIDFromSecurityContext(), menuId));
    }

}