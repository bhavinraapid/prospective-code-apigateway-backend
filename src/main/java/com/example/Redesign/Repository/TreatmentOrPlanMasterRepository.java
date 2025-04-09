package com.example.Redesign.Repository;

import com.example.Redesign.Model.TreatmentOrPlanMaster;
import com.example.Redesign.response.CategoryDetails;
import com.example.Redesign.response.CodeMappingResponse;
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


    @Query("select new com.example.Redesign.response.TextToCUIResponse(lc.cui, mst.treatmentOrPlan, ct.type, mst.id) " +
            "from TreatmentOrPlanCui lc " +
            "join CuiType ct on lc.cuiType = ct.id " +
            "join TreatmentOrPlanMaster mst on mst.id = lc.treatmentOrPlanId " +
            "where mst.id = :id " +
            "order by mst.id, lc.cui")
    List<com.example.Redesign.response.TextToCUIResponse> getTreatmentOrPlanCUIsByMasterId(@Param("id") Integer id);

    @Query("SELECT new com.example.Redesign.response.CodeMappingResponse(" +
            "cmst.code, lc.cui, mst.treatmentOrPlan, ct.type, cmst.id, mst.id) " +
            "FROM TreatmentOrPlanCui lc " +
            "JOIN CuiType ct ON lc.cuiType = ct.id " +
            "JOIN TreatmentOrPlanMaster mst ON mst.id = lc.treatmentOrPlanId " +
            "JOIN TreatmentOrPlanCodeMapper cmp ON cmp.treatmentOrPlanId = mst.id " +
            "JOIN CodeMaster cmst ON cmp.codeId = cmst.id " +
            "WHERE cmst.id = :codeId AND mst.id = :masterId")
    List<CodeMappingResponse> getTreatmentPlanCodeMapping(@Param("codeId") Integer codeId,
                                                              @Param("masterId") Integer masterId);




}
