package com.rumroute.model.voucher;

import com.rumroute.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "voucher")
@Data
public class Voucher implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    private String shot;

    private String spot;

    private String rum;

    private String email;

    private Timestamp redeemdate;

    private boolean enabled;

}
