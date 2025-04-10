package com.example.Redesign.Repository;

import com.example.Redesign.Model.MedicationsCodeMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationsCodeMapperRepository extends JpaRepository<MedicationsCodeMapper, Integer> {


    MedicationsCodeMapper findByCodeIdAndMedicationsId(Integer codeId, Integer medicationsId);
}
