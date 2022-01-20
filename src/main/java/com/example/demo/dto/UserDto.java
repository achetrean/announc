package com.example.demo.dto;

import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private Boolean hasPremium;
    private List<ImageDto> profileImages = new ArrayList<>();
    private ImageDto mainImage;
    private String token;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setRegistrationDate(user.getRegistrationDate());
        userDto.setHasPremium(user.getHasPremium());
        userDto.setMainImage(ImageDto.fromImage(user.getMainImage()));
        userDto.setProfileImages(
                user.getProfileImages()
                        .stream()
                        .map(ImageDto::fromImage)
                        .collect(Collectors.toList())
        );
        return userDto;
    }
}
