package com.treehouseapp.payload;

import com.treehouseapp.model.flowers.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteFlowerResponse {

//    private List<FavoriteMeal> favoriteMealList;
    private List<MenuItem> ListOffavoriteFlowers;
}
