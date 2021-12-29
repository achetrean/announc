package com.example.demo.dto;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnnouncementDto {
    private String title;
    private String authorFullName;
    private LocalDate addingDate;
    private LocalDate expiringDate;
    private String description;
    private String address;
    private String phoneNumber;
    private Double price;
    private Currency currency;

    public static AnnouncementDto fromAnnouncement(Announcement announcement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setTitle(announcement.getTitle());
        announcementDto.setAuthorFullName(announcement.getAuthorFullName());
        return announcementDto;
    }
}
