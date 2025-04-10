package com.example.Redesign.Repository;

import com.example.Redesign.Model.TreatmentOrPlanCodeMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentOrPlanCodeMapperRepository extends JpaRepository<TreatmentOrPlanCodeMapper, Integer> {

    @Query("SELECT t FROM TreatmentOrPlanCodeMapper t WHERE t.codeId = :codeId AND t.treatmentOrPlanId = :treatmentOrPlanId")
    TreatmentOrPlanCodeMapper findByCodeIdAndTreatmentOrPlanId(@Param("codeId") Integer codeId,
                                                               @Param("treatmentOrPlanId") Integer treatmentOrPlanId);

}
