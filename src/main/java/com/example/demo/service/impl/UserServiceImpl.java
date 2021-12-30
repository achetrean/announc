package com.example.demo.service.impl;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.exception.UserExistsException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

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

//        Image image = new Image();
//        try {
//            image.setImage(encodeImageFromFile(new File("userDefaultImage.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        image.setUser(user);
//
//        user.setMainImage(image);
//        user.getProfileImages().add(image);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return UserDto.fromUser(user);
    }

    private static String encodeImageFromFile(File image) throws IOException {
        FileInputStream imageStream = new FileInputStream(image);

        byte[] data = imageStream.readAllBytes();

        String imageString = Base64.getEncoder().encodeToString(data);

        byte[] finalData = imageString.getBytes();
        imageStream.close();

        return imageString;
    }
}
