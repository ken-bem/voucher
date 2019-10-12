package com.rumroute.model.rum;

import com.rumroute.model.brand.Brand;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rum")
@Data
public class Rum {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Brand brand;

}
