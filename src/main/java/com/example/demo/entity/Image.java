package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Image extends BaseEntity{

    @Column(name = "image_bytes")
    @Lob
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
