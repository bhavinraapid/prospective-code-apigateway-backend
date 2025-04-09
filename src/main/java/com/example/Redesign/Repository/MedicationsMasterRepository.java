package com.example.Redesign.Repository;

import com.example.Redesign.Model.MedicationsMaster;
import com.example.Redesign.response.CategoryDetails;
import com.example.Redesign.response.CodeMappingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicationsMasterRepository extends JpaRepository<MedicationsMaster, Integer> {

    @Query("select distinct new com.example.Redesign.response.CategoryDetails(mst.id, mst.medications) " +
            "from MedicationsMaster mst " +
            "inner join MedicationsCodeMapper cmp on cmp.medicationsId = mst.id " +
            "inner join CodeMaster cmst on cmp.codeId = cmst.id " +
            "where cmst.id = :code_id")
    List<CategoryDetails> findCategoryMappingsForMedications(@Param("code_id") int code_id);

    @Query("select new com.example.Redesign.response.TextToCUIResponse(lc.cui, mst.medications, ct.type, mst.id) " +
            "from MedicationsCui lc " +
            "join CuiType ct on lc.cuiType = ct.id " +
            "join MedicationsMaster mst on mst.id = lc.medicationsId " +
            "where mst.id = :id " +
            "order by mst.id, lc.cui")
    List<com.example.Redesign.response.TextToCUIResponse> getMedicationsCUIsByMasterId(@Param("id") Integer id);

    @Query("SELECT new com.example.Redesign.response.CodeMappingResponse(" +
            "cmst.code, lc.cui, mst.medications, ct.type, cmst.id, mst.id) " +
            "FROM MedicationsCui lc " +
            "JOIN CuiType ct ON lc.cuiType = ct.id " +
            "JOIN MedicationsMaster mst ON mst.id = lc.medicationsId " +
            "JOIN MedicationsCodeMapper cmp ON cmp.medicationsId = mst.id " +
            "JOIN CodeMaster cmst ON cmp.codeId = cmst.id " +
            "WHERE cmst.id = :codeId AND mst.id = :masterId")
    List<CodeMappingResponse> getMedicationCodeMapping(@Param("codeId") Integer codeId,
                                                            @Param("masterId") Integer masterId);



}
