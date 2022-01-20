package com.example.demo.controller;


import com.example.demo.dto.AnnouncementDto;
import com.example.demo.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import static com.example.demo.entity.RoleEnum.Fields.*;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/{id}")
    @RolesAllowed({ROLE_USER, ROLE_ADMIN, ROLE_SYSADMIN})
    public ResponseEntity<AnnouncementDto> createAnnouncement(@PathVariable(name = "id") String id,
                                                              @RequestBody AnnouncementDto announcementDto) {
        return ResponseEntity.ok(announcementService.createAnnouncement(id,announcementDto));
    }
}
