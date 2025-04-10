package com.example.Redesign.Repository;

import com.example.Redesign.Model.PhysicalExamCodeMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalExamCodeMapperRepository extends JpaRepository<PhysicalExamCodeMapper, Integer> {

    PhysicalExamCodeMapper findByCodeIdAndPhysicalExamId(Integer codeId, Integer physicalExamId);
}
