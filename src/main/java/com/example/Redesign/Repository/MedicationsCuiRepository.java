package com.example.Redesign.Repository;

import com.example.Redesign.Model.MedicationsCui;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationsCuiRepository extends JpaRepository<MedicationsCui, Integer> {
}
