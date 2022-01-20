package com.example.demo.service.impl;

import com.example.demo.dto.AnnouncementDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class AnnouncementServiceImpl implements AnnouncementService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public AnnouncementDto createAnnouncement(String id, AnnouncementDto announcementDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with id " + id + " was not found"));

        return announcementDto;
    }
}
