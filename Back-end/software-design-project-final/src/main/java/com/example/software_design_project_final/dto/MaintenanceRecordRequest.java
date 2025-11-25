package com.example.software_design_project_final.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;

/**
 * Request payload for creating or updating a maintenance record.
 */
@Getter
@Setter
public class MaintenanceRecordRequest {
    private Integer id;
    private Integer version;
    private Integer transformerId;
    private Integer inspectionId;
    private Integer maintenanceImageId;
    private EngineerFields engineerFields;
    private Map<String, String> anomalyNotes;

    public Map<String, String> getAnomalyNotes() {
        return anomalyNotes == null ? Collections.emptyMap() : anomalyNotes;
    }

    @Getter
    @Setter
    public static class EngineerFields {
        private String inspectorName;
        private String inspectionDate;
        private String status;
        private String voltage;
        private String current;
        private String recommendedAction;
        private String correctiveAction;
        private String additionalRemarks;
        private String followUpDate;
    }
}
