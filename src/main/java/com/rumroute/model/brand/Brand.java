package com.rumroute.model.brand;

import com.rumroute.model.rum.Rum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Brand implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description", length = 100000)
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "logo")
    private String logo;

    @OneToMany(mappedBy = "brand", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Rum> rums = new ArrayList<>();


    public void addRum(Rum rum){
        rum.setBrand(this);
        this.rums.add(rum);
    }

}
