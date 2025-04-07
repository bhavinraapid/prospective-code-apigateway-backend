package com.example.Redesign.Repository;

import com.example.Redesign.Model.MajorMaster;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface MajorMasterRepository extends JpaRepository<MajorMaster, Integer> {

    @Query("SELECT COALESCE(MAX(m.groupId), 0) FROM MajorMaster m")
    Integer findMaxGroupId();

    @Query("SELECT m FROM MajorMaster m WHERE m.codeId = :codeId")
    List<MajorMaster> findByCodeId(int codeId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MajorMaster m WHERE m.codeId = :codeId AND m.groupId = :groupId")
    void deleteByCodeIdAndGroupId(int codeId, int groupId);

}
