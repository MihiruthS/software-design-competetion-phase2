package com.example.software_design_project_final.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lightweight DTO for displaying maintenance record history rows.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecordHistoryItem {
    private Integer id;
    private Integer version;
    private String status;
    private String inspectorName;
    private Integer inspectionId;
    private String inspectionDate;
    private String maintenanceImageName;
    private String createdAt;
    private String updatedAt;
}
