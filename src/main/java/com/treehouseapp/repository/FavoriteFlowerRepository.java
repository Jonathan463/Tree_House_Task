package com.treehouseapp.repository;

import com.treehouseapp.model.flowers.FavoriteFlower;
import com.treehouseapp.model.flowers.MenuItem;
import com.treehouseapp.model.users.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteFlowerRepository extends JpaRepository<FavoriteFlower, Long> {

    @Query(value = "select * from favorite_meals where user_id = ?1", nativeQuery = true)
    List<FavoriteFlower> findAllByUserid(Long userId);

    Optional<FavoriteFlower> findFavoriteMealByUseridAndMenuid(User userId, MenuItem menuId);

    @Query("SELECT count(fm.menuid) as ct FROM FavoriteFlower fm group by fm.menuid order by ct desc")
    List<Long> findTopByMenuidOrderByMenuidDesc(Pageable pageable);
}
