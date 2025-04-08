package com.example.Redesign.Repository;

import com.example.Redesign.Model.LabsMaster;
import com.example.Redesign.response.CategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabsMasterRepository extends JpaRepository<LabsMaster, Integer> {

    @Query("select distinct new com.example.Redesign.response.CategoryDetails(mst.id, mst.labs) " +
            "from LabsMaster mst " +
            "inner join LabDataCodeMapper cmp on cmp.labs = mst.id " +
            "inner join CodeMaster cmst on cmp.codeId = cmst.id " +
            "where cmst.id = :code_id")
    List<CategoryDetails> findCategoryMappingsForLabs(@Param("code_id") int code_id);

    @Query("select new com.example.Redesign.response.TextToCUIResponse(lc.cui, mst.labs, ct.type, mst.id) " +
            "from LabsCui lc " +
            "join CuiType ct on lc.cuiType = ct.id " +
            "join LabsMaster mst on mst.id = lc.labsId " +
            "where mst.id = :id")
    List<com.example.Redesign.response.TextToCUIResponse> getLabsCUIsByMasterId(@Param("id") Integer id);


}
