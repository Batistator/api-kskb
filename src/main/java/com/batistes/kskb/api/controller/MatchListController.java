package com.batistes.kskb.api.controller;

import com.batistes.kskb.api.service.MatchListService;
import com.batistes.kskb.api.service.SambaService;

import java.io.InputStream;
import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class MatchListController {

    @Autowired
    private MatchListService MatchListService;

    @Autowired
    private SambaService sambaService;

    private static final Logger logger = LoggerFactory.getLogger(MatchListController.class);

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

    @GetMapping("/download/{demoFile}")
    public ResponseEntity<InputStreamResource> descargarArchivo(@PathVariable String demoFile) {
        try {
            InputStream stream = sambaService.getSambaFileStream(demoFile);
            InputStreamResource resource = new InputStreamResource(stream);

            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + demoFile + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
