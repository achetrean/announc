package com.example.demo.service.impl;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.exception.UserExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDto register(RegistrationRequest request) {
        Optional<User> userByEmail = userRepository.findByEmail(request.getEmail());
        if (userByEmail.isPresent()) {
            throw new UserExistsException("User with " + request.getEmail() + " already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBirthDate(request.getBirthDate());
        user.setRegistrationDate(LocalDate.now());
        user.setHasPremium(false);

        Image image = new Image();
        try {
            image.setImageBytes(encodeImageFromFile(new File("userDefaultImage.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setUser(user);

        user.setMainImage(image);
        user.getProfileImages().add(image);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return UserDto.fromUser(user);
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with " + id + " was not found"));
        return UserDto.fromUser(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with id " + id + " was not found"));

        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }

        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }

        if (userDto.getBirthDate() != null) {
            user.setBirthDate(userDto.getBirthDate());
        }

        if (userDto.getRegistrationDate() != null) {
            user.setRegistrationDate(userDto.getRegistrationDate());
        }

        if (userDto.getHasPremium() != null) {
            user.setHasPremium(userDto.getHasPremium());
        }

        if (userDto.getMainImage().getImageBytes() != null) {

            Image image = new Image();
            image.setImageBytes(userDto.getMainImage().getImageBytes().getBytes());
            user.getProfileImages().add(image);
            user.setMainImage(image);
        }
        return UserDto.fromUser(user);
    }

    private static byte[] encodeImageFromFile(File image) throws IOException {
        FileInputStream imageStream = new FileInputStream(image);

        byte[] data = imageStream.readAllBytes();

        String imageString = Base64.getEncoder().encodeToString(data);

        byte[] finalData = imageString.getBytes();
        imageStream.close();

        return finalData;
    }
}
