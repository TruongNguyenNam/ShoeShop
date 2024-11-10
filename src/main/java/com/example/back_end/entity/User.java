package com.example.back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;


@Entity
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;
    private boolean deleted;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Cart> carts;
    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlists;
    @OneToMany(mappedBy = "user")
    private List<ActivityLog> activityLogs;
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;
    @OneToMany(mappedBy = "user")
    private List<CustomerService> customerServices;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;
    @OneToMany(mappedBy = "user")
    private List<Reward> rewards;


    @NoArgsConstructor
    @Getter
    public enum Role {
        ADMIN,
        CUSTOMER
    }
}
