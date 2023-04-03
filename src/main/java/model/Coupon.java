package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "registration_date")
    @NonNull
    private Date registrationDate;
    @Column(name = "expiry_time")
    @NonNull
    private int expiryTime;
}
