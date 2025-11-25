package com.example.software_design_project_final.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * MaintenanceRecord entity stores finalized maintenance reports for a transformer inspection.
 */
@Entity
@Table(name = "maintenance_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transformer_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_maintenance_record_transformer"))
    private Transformer transformer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_id",
            foreignKey = @ForeignKey(name = "FK_maintenance_record_inspection"))
    private Inspection inspection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_image_id",
            foreignKey = @ForeignKey(name = "FK_maintenance_record_image"))
    private Image maintenanceImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private RecordStatus status = RecordStatus.OK;

    @Column(name = "inspector_name")
    private String inspectorName;

    @Column(name = "inspection_date")
    private LocalDate inspectionDate;

    @Column(name = "voltage")
    private String voltage;

    @Column(name = "current")
    private String current;

    @Column(name = "recommended_action", columnDefinition = "TEXT")
    private String recommendedAction;

    @Column(name = "corrective_action", columnDefinition = "TEXT")
    private String correctiveAction;

    @Column(name = "additional_remarks", columnDefinition = "TEXT")
    private String additionalRemarks;

    @Column(name = "follow_up_date")
    private LocalDate followUpDate;

    @Column(name = "record_version", nullable = false)
    private Integer version = 1;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaintenanceRecordNote> anomalyNotes = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        if (version == null) {
            version = 1;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum RecordStatus {
        OK("OK"),
        NEEDS_MAINTENANCE("Needs Maintenance"),
        URGENT_ATTENTION("Urgent Attention"),
        DRAFT("Draft");

        private final String label;

        RecordStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public static RecordStatus fromLabel(String value) {
            if (!StringUtils.hasText(value)) {
                return OK;
            }
            String trimmed = value.trim();
            String normalized = trimmed.replace('_', ' ').toLowerCase(Locale.ROOT);
            for (RecordStatus status : values()) {
                if (status.name().equalsIgnoreCase(trimmed) || status.label.toLowerCase(Locale.ROOT).equals(normalized)) {
                    return status;
                }
            }
            switch (normalized) {
                case "needs maintenance":
                    return NEEDS_MAINTENANCE;
                case "urgent attention":
                    return URGENT_ATTENTION;
                case "draft":
                    return DRAFT;
                default:
                    return OK;
            }
        }
    }
}
