package com.batistes.kskb.api.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.batistes.kskb.api.service.TitlesService;

@RestController
@RequestMapping("/api/titles")
public class TitlesController {
    @Autowired
    private TitlesService titlesService;

    @GetMapping(value = "/getAllTitles" , produces = "application/json;charset=UTF-8")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getWeaponData(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        try {
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            return ResponseEntity.ok(titlesService.findAllTitles(startDate, endDate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
