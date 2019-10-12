package com.rumroute.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rumroute.model.role.Role;
import com.rumroute.model.voucher.Voucher;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Transient
    private String confirmPassword;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "registerDate")
    private Timestamp registerDate;

    @Column(name = "lastRedeem")
    private Date lastRedeem;

    @Column(name = "credits")
    private int credits;

    @Column(name = "reminder")
    private boolean sendReminder;

    @Column(name = "enable")
    private boolean hasRedeemed;

    @OneToMany(mappedBy = "user")
    private List<Voucher> vouchers = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
