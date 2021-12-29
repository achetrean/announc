package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement extends BaseEntity{
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author_full_name", nullable = false)
    private String authorFullName;

    @Column(name = "adding_date", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate addingDate;

    @Column(name = "expiringDate", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expiringDate;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;
}
