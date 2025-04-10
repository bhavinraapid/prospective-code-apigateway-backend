package com.example.Redesign.Repository;

import com.example.Redesign.Model.LabDataCodeMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LabDataCodeMapperRepository extends JpaRepository<LabDataCodeMapper, Integer> {

    LabDataCodeMapper findByCodeIdAndLabs(Integer codeId, Integer labs);

}
