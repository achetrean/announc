package com.example.demo.dto;

import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private Boolean hasPremium;
    private List<Image> profileImages = new ArrayList<>();
    private Image mainImage;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setRegistrationDate(user.getRegistrationDate());
        userDto.setHasPremium(user.getHasPremium());
        userDto.setProfileImages(user.getProfileImages());
        userDto.setMainImage(user.getMainImage());
        return userDto;
    }
}
