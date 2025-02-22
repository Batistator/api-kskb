package com.batistes.kskb.api.controller;

import com.batistes.kskb.api.service.MatchListService;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class MatchListController {

    @Autowired
    private MatchListService MatchListService;

    //TODO: Ver si se puede modificar el java.sql.Date por java.util.Date para evitar tanta conversión.

    @GetMapping("/getMatchListData")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getMatchListData(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        try {
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            return ResponseEntity.ok(MatchListService.getMatchListData(startDate, endDate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
