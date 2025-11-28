package com.example.software_design_project_final.repository;

import com.example.software_design_project_final.dao.MaintenanceRecord;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for MaintenanceRecord entities.
 */
@Repository
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Integer> {

    @EntityGraph(attributePaths = {"transformer", "inspection", "maintenanceImage", "anomalyNotes"})
    Optional<MaintenanceRecord> findTopByTransformer_IdAndInspection_IdOrderByCreatedAtDesc(Integer transformerId, Integer inspectionId);

    @EntityGraph(attributePaths = {"transformer", "inspection", "maintenanceImage", "anomalyNotes"})
    Optional<MaintenanceRecord> findTopByTransformer_IdOrderByCreatedAtDesc(Integer transformerId);

    @EntityGraph(attributePaths = {"transformer", "inspection", "maintenanceImage", "anomalyNotes"})
    List<MaintenanceRecord> findByTransformer_IdOrderByCreatedAtDesc(Integer transformerId);

    @EntityGraph(attributePaths = {"transformer", "inspection", "maintenanceImage", "anomalyNotes"})
    List<MaintenanceRecord> findByTransformer_IdAndInspection_IdOrderByCreatedAtDesc(Integer transformerId, Integer inspectionId);

    @Override
    @EntityGraph(attributePaths = {"transformer", "inspection", "maintenanceImage", "anomalyNotes"})
    Optional<MaintenanceRecord> findById(Integer id);
}
