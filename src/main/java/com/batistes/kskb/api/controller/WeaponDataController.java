package com.batistes.kskb.api.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.batistes.kskb.api.service.WeaponDataService;

@RestController
@RequestMapping("/api/weapons")
public class WeaponDataController {
    @Autowired
    private WeaponDataService weaponDataService;

    //TODO: Ver si se puede modificar el java.sql.Date por java.util.Date para evitar tanta conversión.

    @GetMapping("/getWeaponData")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getWeaponData(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        try {
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            return ResponseEntity.ok(weaponDataService.getWeaponData(startDate, endDate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    @GetMapping("/getWeaponCounts")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getWeaponCounts(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        try {
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            return ResponseEntity.ok(weaponDataService.getWeaponCount(startDate, endDate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
