package com.example.Redesign.Repository;

import com.example.Redesign.Model.LabsCui;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabsCuiRepository extends JpaRepository<LabsCui, Integer> {
}
