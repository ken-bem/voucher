package com.rumroute.model.spot;

import com.rumroute.model.category.Category;
import com.rumroute.model.drink.Drink;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Spot")
@Data
public class Spot implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    private String email;

    //Todo: Create an address embedable
    private String Address;

    //Todo: create a spot status
    private String status;

    private String phone;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drink> drinks = new ArrayList<>();

    @Lob
    @Column(length = 100000)
    private String  description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Spot_Category", joinColumns = {@JoinColumn(name = "spotcode")},
        inverseJoinColumns = {@JoinColumn(name = "code")} )
    private List<Category> categories = new ArrayList<>();

    private boolean hasWIFI;

    private String featuredImage;

    @ElementCollection
    private List<String> slideshow = new ArrayList<>();

}

