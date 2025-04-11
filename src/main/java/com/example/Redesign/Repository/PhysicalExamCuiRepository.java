package com.example.Redesign.Repository;

import com.example.Redesign.Model.PhysicalExamCui;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalExamCuiRepository extends JpaRepository<PhysicalExamCui, Integer> {
}
