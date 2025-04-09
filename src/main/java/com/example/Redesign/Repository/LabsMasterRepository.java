package com.example.Redesign.Repository;

import com.example.Redesign.Model.LabsMaster;
import com.example.Redesign.response.CategoryDetails;
import com.example.Redesign.response.CodeMappingResponse;
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

    @Query("SELECT new com.example.Redesign.response.CodeMappingResponse(" +
            "cmst.code, lc.cui, mst.labs, ct.type,cmp.relationship, u.unit, cmp.value1, cmp.value2, cmst.id, mst.id) " +
            "FROM LabsCui lc " +
            "JOIN CuiType ct ON lc.cuiType = ct.id " +
            "JOIN LabsMaster mst ON mst.id = lc.labsId " +
            "JOIN LabDataCodeMapper cmp ON cmp.labs = mst.id " +
            "JOIN Units u ON u.id = cmp.unit " +
            "JOIN CodeMaster cmst ON cmp.codeId = cmst.id " +
            "WHERE cmst.id = :codeId AND mst.id = :masterId")
    List<CodeMappingResponse> getLabsCodeMappingData(@Param("codeId") Integer codeId,
                                                    @Param("masterId") Integer masterId);



}
