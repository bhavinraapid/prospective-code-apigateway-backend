package com.example.Redesign.Repository;

import com.example.Redesign.Model.PhysicalExamCui;
import com.example.Redesign.Model.TreatmentOrPlanCui;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentOrPlanCuiRepository extends JpaRepository<TreatmentOrPlanCui, Integer> {
}
