package com.rumroute.model.drink;

import com.rumroute.model.category.Category;
import com.rumroute.model.spot.Spot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "drink")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drink implements Serializable{

    @Id
    @Column(name = "slug")
    private String code;

    @ManyToOne
    private Spot spot;

    @Lob
    @Column(name = "description", length = 100000)
    private String Description;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private Flavor flavor;

    @ManyToOne
    private Category category;
}
