package com.batistes.kskb.api.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.batistes.kskb.api.service.TotalDataService;

@RestController
@RequestMapping("/api/total")
public class TotalDataController {
    @Autowired
    private TotalDataService totalDataService;

    //TODO: Ver si se puede modificar el java.sql.Date por java.util.Date para evitar tanta conversión.

    @GetMapping("/getTotalData")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getMatchListData(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        try {
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            return ResponseEntity.ok(totalDataService.getTotalData(startDate, endDate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
