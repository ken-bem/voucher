package com.rumroute.model.category;


import com.rumroute.model.drink.Drink;
import com.rumroute.model.spot.Spot;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
@Data
public class Category {

    @Id
    private String code;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(length = 100000)
    private String  description;

    @Lob
    @Column(length = 100000)
    private String  routeTitle;

    @Column(name = "color")
    @Pattern(regexp = "#[0-9a-fA-F]{6}")
    private String color_code;

    private String icon;

    @OneToMany(mappedBy = "category")
    private List<Drink> drinks = new ArrayList<>();

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Spot> spots = new ArrayList<>();
}
