package com.batistes.kskb.api.controller;

import com.batistes.kskb.api.repository.KillsRepository;
import com.batistes.kskb.api.service.DashboardService;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private KillsRepository killsRepository;

    //TODO: Ver si se puede modificar el java.sql.Date por java.util.Date para evitar tanta conversión.

    @GetMapping("/getDashboardData")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getDashboardData(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        try {
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            return ResponseEntity.ok(dashboardService.getDashboardData(startDate, endDate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    @GetMapping("/tst")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> tst() {
        try {
            return ResponseEntity.ok(killsRepository.countKillsByKillerName("ShinChan"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
