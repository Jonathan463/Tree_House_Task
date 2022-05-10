package com.treehouseapp.service.serviceInterfaces;

import com.treehouseapp.model.flowers.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoriteFlowerService {

    MenuItem createFavoriteFlower(Long userId, Long menuId);

    String removeFromFlower(Long userId, Long menuId);

//    public List<FavoriteMeal> getAllFavoriteMealsByAUser(Long userId);

    public List<MenuItem> getAllFavoriteFlowersByAUser(Long userId);

    public MenuItem getAParticularFavoriteFlower(Long userId, Long menuId);

}
