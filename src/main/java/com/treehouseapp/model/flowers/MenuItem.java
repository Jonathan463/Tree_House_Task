package com.treehouseapp.model.flowers;

import com.treehouseapp.model.basemodel.BaseModel;
import com.treehouseapp.model.enums.MenuCategory;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "menu_items")
public class MenuItem extends BaseModel {

    @NotNull
    private String name;

    @NotNull
    private String image;


    @NotNull
    private String description;

    @NotNull
    private Double price;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private MenuCategory category;


    public MenuItem(String name, String image, String description, Double price, MenuCategory category) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
