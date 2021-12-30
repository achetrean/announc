package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Image extends BaseEntity{

    @JsonIgnore
    @Column(name = "image_bytes")
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] imageBytes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
