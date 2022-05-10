package com.treehouseapp.service.serviceImpl;

import com.treehouseapp.exception.BadRequestException;
import com.treehouseapp.model.flowers.FavoriteFlower;
import com.treehouseapp.model.flowers.MenuItem;
import com.treehouseapp.model.users.User;
import com.treehouseapp.repository.FavoriteFlowerRepository;
import com.treehouseapp.repository.MenuItemRepository;
import com.treehouseapp.repository.UserRepository;
import com.treehouseapp.service.serviceInterfaces.FavoriteFlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteMenuServiceImpl implements FavoriteFlowerService {

    private final FavoriteFlowerRepository favoriteFlowerRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public FavoriteMenuServiceImpl(FavoriteFlowerRepository favoriteFlowerRepository, UserRepository userRepository, MenuItemRepository menuItemRepository) {
        this.favoriteFlowerRepository = favoriteFlowerRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
    }


    @Override
    public MenuItem createFavoriteFlower(Long userId, Long menuId) {
        Optional<User> user = userRepository.findUserById(userId);
        Optional<MenuItem> menuItem = menuItemRepository.findMenuItemById(menuId);
        if (user.isEmpty() || menuItem.isEmpty()) {
            throw new BadRequestException("User or MenuItem not found");
        }

        Optional<FavoriteFlower> favoriteMeal = favoriteFlowerRepository.findFavoriteMealByUseridAndMenuid(user.get(), menuItem.get());
        if (favoriteMeal.isPresent()) {
            throw new BadRequestException("Item is already a favorite");
        };
        FavoriteFlower favoriteFlower = new FavoriteFlower(user.get(), menuItem.get());
         favoriteFlowerRepository.save(favoriteFlower);
         return menuItem.get();
    }

    @Override
    public String removeFromFlower(Long userId, Long menuId) {
        Optional<User> user = userRepository.findUserById(userId);
        Optional<MenuItem> menuItem = menuItemRepository.findMenuItemById(menuId);

        if (user.isEmpty() || menuItem.isEmpty()) throw new BadRequestException("User or MenuItem not found");

        FavoriteFlower favoriteMeal = favoriteFlowerRepository.findFavoriteMealByUseridAndMenuid(user.get(), menuItem.get())
                .orElseThrow(()->new BadRequestException("favorite meal with id: " + menuId + " does not exist"));
        favoriteFlowerRepository.delete(favoriteMeal);
        return "favorite meal with " + menuId + " has been removed from favorite.";
    }

//    public List<FavoriteMeal> getAllFavoriteMealsByAUser(Long userId) {
//        Optional<User> user = userRepository.findUserById(userId);
//        System.out.println("Getting all favorite meals by user: "+userId);
//        if (user.isEmpty()) {
//            System.out.println("User: "+userId+" does not exist");
//            throw new BadRequestException("User or MenuItem not found");
//        }
//
//        List<FavoriteMeal> allByUserid = favoriteMealRepository.findAllByUserid(user.get().getId());
//
//        System.out.println(allByUserid);
//
//        return allByUserid;
//    }

    public List<MenuItem> getAllFavoriteFlowersByAUser(Long userId) {
        Optional<User> user = userRepository.findUserById(userId);
        System.out.println("Getting all favorite meals by user: "+userId);
        if (user.isEmpty()) {
            System.out.println("User: "+userId+" does not exist");
            throw new BadRequestException("User or MenuItem not found");
        }

        List<FavoriteFlower> allByUserid = favoriteFlowerRepository.findAllByUserid(user.get().getId());
        List<MenuItem> itemList = allByUserid.stream()
                        .map(FavoriteFlower::getMenuid)
                        .collect(Collectors.toList());
        System.out.println(itemList);

        return itemList;
        }

    public MenuItem getAParticularFavoriteFlower(Long userId, Long menuId){
        Optional<User> user = userRepository.findUserById(userId);
        Optional<MenuItem> menuItem = menuItemRepository.findMenuItemById(menuId);

        if (user.isEmpty() || menuItem.isEmpty()) throw new BadRequestException("User or MenuItem not found");

       FavoriteFlower favMeal =  favoriteFlowerRepository.findFavoriteMealByUseridAndMenuid(user.get(), menuItem.get())
                .orElseThrow( ()-> new BadRequestException("Favorite flower does not exist"));

       return menuItemRepository.findMenuItemById(favMeal.getMenuid().getId()).get();
    }

}
