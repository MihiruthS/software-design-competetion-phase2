package com.example.software_design_project_final.controller;

import com.example.software_design_project_final.dto.MaintenanceRecordHistoryItem;
import com.example.software_design_project_final.dto.MaintenanceRecordRequest;
import com.example.software_design_project_final.dto.MaintenanceRecordResponse;
import com.example.software_design_project_final.service.MaintenanceRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing endpoints for maintenance record management.
 */
@RestController
@RequestMapping("/api/maintenance-records")
@RequiredArgsConstructor
public class MaintenanceRecordController {

    private final MaintenanceRecordService maintenanceRecordService;

    @GetMapping("/draft")
    public ResponseEntity<MaintenanceRecordResponse> getDraft(@RequestParam("transformerId") Integer transformerId,
                                                              @RequestParam(value = "inspectionId", required = false) Integer inspectionId) {
        MaintenanceRecordResponse draft = maintenanceRecordService.loadDraft(transformerId, inspectionId);
        return ResponseEntity.ok(draft);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<MaintenanceRecordResponse> getRecord(@PathVariable Integer recordId) {
        MaintenanceRecordResponse response = maintenanceRecordService.loadRecord(recordId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transformers/{transformerId}")
    public ResponseEntity<List<MaintenanceRecordHistoryItem>> getHistory(@PathVariable Integer transformerId,
                                                                         @RequestParam(value = "inspectionId", required = false) Integer inspectionId) {
        List<MaintenanceRecordHistoryItem> history = maintenanceRecordService.loadHistory(transformerId, inspectionId);
        return ResponseEntity.ok(history);
    }

    @PostMapping
    public ResponseEntity<MaintenanceRecordResponse> createRecord(@RequestBody MaintenanceRecordRequest request) {
        MaintenanceRecordResponse response = maintenanceRecordService.saveRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<MaintenanceRecordResponse> updateRecord(@RequestBody MaintenanceRecordRequest request) {
        MaintenanceRecordResponse response = maintenanceRecordService.saveRecord(request);
        return ResponseEntity.ok(response);
    }
}
