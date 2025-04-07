package com.example.Redesign.utility;


import com.example.Redesign.Model.*;
import com.example.Redesign.Repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CodingDbUtility {

    @Autowired
    private CodeMasterRepository codeMasterRepository;
    private final Map<Integer, String> codeMasterMap = new HashMap<>();

    @Autowired
    private MajorMasterRepository majorMasterRepository;
    private final Map<Integer, Set<Integer>> codeIdToGroupIds = new HashMap<>();
    private final Map<Integer, List<MajorMaster>> groupIdToMajorMaster = new HashMap<>();

    @Autowired
    private LabsMasterRepository labsMasterRepository;
    private final Map<Integer, String> labsMasterMap = new HashMap<>();
    @Autowired
    private PhysicalExamMasterRepository physicalExamMasterRepository;
    private final Map<Integer, String> physicalExamMasterMap = new HashMap<>();
    @Autowired
    private MedicationsMasterRepository medicationsMasterRepository;
    private final Map<Integer, String> medicationsMasterMap = new HashMap<>();
    @Autowired
    private TreatmentOrPlanMasterRepository treatmentOrPlanMasterRepository;
    private final Map<Integer, String> treatmentOrPlanMasterMap = new HashMap<>();


    @PostConstruct
    private void loadknowledge()
    {

        /* Load CodeMaster Map */
        List<CodeMaster> codeMasterList = codeMasterRepository.findAll();
        codeMasterMap.clear();
        codeMasterMap.putAll(codeMasterList.stream()
                .collect(Collectors.toMap(CodeMaster::getId, CodeMaster::getCode)));


        /* Load labsMaster Map */
        List<LabsMaster> labsMasterList = labsMasterRepository.findAll();
        labsMasterMap.clear();
        labsMasterMap.putAll(labsMasterList.stream().collect(Collectors.toMap(LabsMaster::getId, LabsMaster::getLabs)));


        /* Load treatmentOrPlanMasterMap Map */
        List<TreatmentOrPlanMaster> treatmentOrPlanMasterList = treatmentOrPlanMasterRepository.findAll();
        treatmentOrPlanMasterMap.clear();
        treatmentOrPlanMasterMap.putAll(treatmentOrPlanMasterList.stream()
                .collect(Collectors.toMap(TreatmentOrPlanMaster::getId, TreatmentOrPlanMaster::getTreatmentOrPlan)));


        /* Load medicationsMasterMap Map */
        List<MedicationsMaster> medicationsMasterList = medicationsMasterRepository.findAll();
        medicationsMasterMap.clear();
        medicationsMasterMap.putAll(medicationsMasterList.stream()
                .collect(Collectors.toMap(MedicationsMaster::getId, MedicationsMaster::getMedications)));


        /* Load physicalExamMasterMap Map */
        List<PhysicalExamMaster> physicalExamMasterList = physicalExamMasterRepository.findAll();
        physicalExamMasterMap.clear();
        physicalExamMasterMap.putAll(physicalExamMasterList.stream()
                .collect(Collectors.toMap(PhysicalExamMaster::getId , PhysicalExamMaster::getPhysicalExam)));

        /* Load MajorMaster Map */
        List<MajorMaster> majorMasterList = majorMasterRepository.findAll();

        codeIdToGroupIds.clear();
        codeIdToGroupIds.putAll(majorMasterList.stream()
                .collect(Collectors.groupingBy(MajorMaster::getCodeId,
                        Collectors.mapping(MajorMaster::getGroupId, Collectors.toSet()))));

        groupIdToMajorMaster.clear();
        groupIdToMajorMaster.putAll(majorMasterList.stream()
                .collect(Collectors.groupingBy(MajorMaster::getGroupId, Collectors.toList())));


    }

    public Map<Integer, String> getCodeMasterMap()
    {
        return this.codeMasterMap;
    }

    public Map<Integer, String> getLabsMasterMap()
    {
        return this.labsMasterMap;
    }

    public Map<Integer, String> getPhysicalExamMasterMap()
    {
        return this.physicalExamMasterMap;
    }


    public Map<Integer, String> getMedicationsMasterMap()
    {
        return this.medicationsMasterMap;
    }

    public Map<Integer, String> getTreatmentOrPlanMasterMap()
    {
        return this.treatmentOrPlanMasterMap;
    }

    public Map<Integer, Set<Integer>> getCodeIdToGroupIds()
    {
        return this.codeIdToGroupIds;
    }

    public Map<Integer, List<MajorMaster>> getGroupIdToMajorMaster()
    {
        return this.groupIdToMajorMaster;
    }
}
