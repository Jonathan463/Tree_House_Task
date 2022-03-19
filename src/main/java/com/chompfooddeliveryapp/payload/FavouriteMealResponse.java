package com.chompfooddeliveryapp.payload;

import com.chompfooddeliveryapp.model.meals.FavoriteMeal;
import com.chompfooddeliveryapp.model.meals.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteMealResponse {

//    private List<FavoriteMeal> favoriteMealList;
    private List<MenuItem> ListOffavoriteMeals;
}
