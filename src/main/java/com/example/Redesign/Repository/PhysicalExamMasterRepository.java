package com.example.Redesign.Repository;

import com.example.Redesign.Model.PhysicalExamMaster;
import com.example.Redesign.response.CategoryDetails;
import com.example.Redesign.response.TextToCUIResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhysicalExamMasterRepository extends JpaRepository<PhysicalExamMaster, Integer> {


    @Query("select distinct new com.example.Redesign.response.CategoryDetails(mst.id, mst.physicalExam) " +
            "from PhysicalExamMaster mst " +
            "inner join PhysicalExamCodeMapper cmp on cmp.physicalExamId = mst.id " +
            "inner join CodeMaster cmst on cmp.codeId = cmst.id " +
            "where cmst.id = :code_id")
    List<CategoryDetails> findCategoryMappingsForPhysicalExam(@Param("code_id") int code_id);

    @Query("select new com.example.Redesign.response.TextToCUIResponse(lc.cui, mst.physicalExam, ct.type, mst.id) " +
            "from PhysicalExamCui lc " +
            "join CuiType ct on lc.cuiType = ct.id " +
            "join PhysicalExamMaster mst on mst.id = lc.physicalExamId " +
            "where mst.id = :id")
    List<TextToCUIResponse> getPhysicalExamCUIsByMasterId(@Param("id") Integer id);


}
