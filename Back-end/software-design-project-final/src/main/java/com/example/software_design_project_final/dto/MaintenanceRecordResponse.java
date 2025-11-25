package com.example.software_design_project_final.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Response payload representing a maintenance record with related metadata.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecordResponse {
    private Integer id;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TransformerSummary transformer;
    private InspectionSummary inspection;
    private ImageSummary maintenanceImage;
    private EngineerFields engineerFields;
    private List<AnomalyDto> anomalies = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MaintenanceRecordHistoryItem> history = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransformerSummary {
        private Integer id;
        private String transformerNo;
        private String location;
        private String region;
        private String poleNo;
        private String transformerType;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InspectionSummary {
        private Integer id;
        private String inspectionNo;
        private String branch;
        private String status;
        private String inspectedBy;
        private String timestamp;
        private String maintenanceDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageSummary {
        private Integer id;
        private String fileName;
        private String filePath;
        private String imageType;
        private String envCondition;
        private String uploadDate;
        private String createdAt;
        private Integer inspectionId;
        private Integer transformerId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AnomalyDto {
        private Integer id;
        private Integer anomalyId;
        private Integer imageId;
        private String errorType;
        private Double confidence;
        private String confidenceLabel;
        private String severityLevel;
        private String annotationType;
        private BoundingBox boundingBox;
        private String engineerNote;
        private String createdAt;
        private Boolean archived;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoundingBox {
        private Double x1;
        private Double y1;
        private Double x2;
        private Double y2;
    }
}
