package com.example.demo.service;

import com.example.demo.dto.AnnouncementDto;

public interface AnnouncementService {
    AnnouncementDto createAnnouncement(String id, AnnouncementDto announcementDto);
}
