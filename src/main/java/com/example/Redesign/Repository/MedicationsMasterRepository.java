package com.example.Redesign.Repository;

import com.example.Redesign.Model.MedicationsMaster;
import com.example.Redesign.response.CategoryDetails;
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


}
