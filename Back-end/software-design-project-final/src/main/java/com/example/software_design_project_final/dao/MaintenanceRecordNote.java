package com.example.software_design_project_final.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * MaintenanceRecordNote stores engineer notes for a specific anomaly within a maintenance record.
 */
@Entity
@Table(name = "maintenance_record_notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecordNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_record_note_record"))
    private MaintenanceRecord record;

    @Column(name = "annotation_id")
    private Integer annotationId;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "anomaly_snapshot", columnDefinition = "TEXT")
    private String anomalySnapshot;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
