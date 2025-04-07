package com.example.Redesign.Repository;

import com.example.Redesign.Model.CodeMaster;
import com.example.Redesign.response.CategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeMasterRepository extends JpaRepository<CodeMaster, Integer> {

    @Query("SELECT DISTINCT new com.example.Redesign.response.CategoryDetails(id, code) " +
            "FROM CodeMaster")
    List<CategoryDetails> findCategoryMappingsForMustRequireCode();


}
