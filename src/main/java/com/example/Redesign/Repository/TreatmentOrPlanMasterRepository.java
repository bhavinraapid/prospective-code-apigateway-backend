package com.example.Redesign.Repository;

import com.example.Redesign.Model.TreatmentOrPlanMaster;
import com.example.Redesign.response.CategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreatmentOrPlanMasterRepository extends JpaRepository<TreatmentOrPlanMaster, Integer> {

    @Query("SELECT DISTINCT new com.example.Redesign.response.CategoryDetails(mst.id, mst.treatmentOrPlan) " +
            "FROM TreatmentOrPlanMaster mst " +
            "INNER JOIN TreatmentOrPlanCodeMapper cmp ON cmp.treatmentOrPlanId = mst.id " +
            "INNER JOIN CodeMaster cmst ON cmp.codeId = cmst.id " +
            "WHERE cmst.id = :code_id")
    List<CategoryDetails> findCategoryMappingsForTreatmentOrPlan(@Param("code_id") int code_id);


}
