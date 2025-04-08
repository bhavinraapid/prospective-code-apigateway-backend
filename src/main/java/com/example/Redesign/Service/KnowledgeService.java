package com.example.Redesign.Service;

import com.example.Redesign.DTO.MasterDataItem;
import com.example.Redesign.Model.*;
import com.example.Redesign.Repository.*;
import com.example.Redesign.utility.CodingDbUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KnowledgeService {

    @Autowired
    private CodeMasterRepository codeMasterRepository;

    @Autowired
    private ClientMasterRepository clientMasterRepository;

    @Autowired
    private MajorMasterRepository majorMasterRepository;

    @Autowired
    private CodingDbUtility codingDbUtility;

    @Autowired
    private LabsMasterRepository labsMasterRepository;

    @Autowired
    private PhysicalExamMasterRepository physicalExamMasterRepository;

    @Autowired
    private MedicationsMasterRepository medicationsMasterRepository;

    @Autowired
    private TreatmentOrPlanMasterRepository treatmentOrPlanMasterRepository;

    public List<MasterDataItem> fetchMasterItems(String type) {

        List<MasterDataItem> masterDataItemList = new ArrayList<>();
        switch (type)
        {
            case "labs":
                masterDataItemList = codingDbUtility.getLabsMasterMap().entrySet().stream()
                        .map(entry -> new MasterDataItem(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList());
                break;
            case "physicalExam":
                masterDataItemList = codingDbUtility.getPhysicalExamMasterMap().entrySet().stream()
                        .map(entry -> new MasterDataItem(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList());
                break;
            case "treatment":
                masterDataItemList = codingDbUtility.getTreatmentOrPlanMasterMap().entrySet().stream()
                        .map(entry -> new MasterDataItem(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList());
                break;
            case "medications":
                masterDataItemList = codingDbUtility.getMedicationsMasterMap().entrySet().stream()
                        .map(entry -> new MasterDataItem(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Type Miss match");
                break;
        }
        return masterDataItemList;
    }

    public String addToCodeMaster(String codeText) {
        try {
            System.out.println(codeText.trim().toUpperCase());
            if (codingDbUtility.getCodeMasterMap().containsValue(codeText.toUpperCase())) {
                System.out.println("We Are here ");
                return "Code Already Exists in Code master";
            }
//            CodeMaster code = new CodeMaster();
//            code.setCode(codeText.trim().toUpperCase());
//            codeMasterRepository.save(code);
            return "Code Saved SuccessFully";
        } catch (Exception e) {
            System.out.println("Error While Save to CodeMaster : " + e.getMessage());
            return "Code is Not Saved Succesfully";
        }
    }

    public MasterDataItem addToMaster(String type, String text) {

        switch (type) {
            case "labs":
                Integer existingKey = codingDbUtility.getLabsMasterMap().entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equalsIgnoreCase(text))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

                if (existingKey != null) {
                    return new MasterDataItem(existingKey, codingDbUtility.getLabsMasterMap().get(existingKey));
                } else {
                    LabsMaster labsMaster = new LabsMaster();
                    labsMaster.setLabs(text);
                    labsMasterRepository.save(labsMaster);
                    addCUIMapping(type, text);
                    return new MasterDataItem(labsMaster.getId(), labsMaster.getLabs());
                }

            case "physicalExam":
                Integer existingKey1 = codingDbUtility.getPhysicalExamMasterMap().entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equalsIgnoreCase(text))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

                if (existingKey1 != null) {
                    return new MasterDataItem(existingKey1, codingDbUtility.getPhysicalExamMasterMap().get(existingKey1));
                } else {
                    PhysicalExamMaster physicalExamMaster = new PhysicalExamMaster();
                    physicalExamMaster.setPhysicalExam(text);
                    physicalExamMasterRepository.save(physicalExamMaster);
                    addCUIMapping(type, text);
                    return new MasterDataItem(physicalExamMaster.getId(), physicalExamMaster.getPhysicalExam());
                }

            case "treatment":
                Integer existingKey2 = codingDbUtility.getTreatmentOrPlanMasterMap().entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equalsIgnoreCase(text))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

                if (existingKey2 != null) {
                    return new MasterDataItem(existingKey2, codingDbUtility.getTreatmentOrPlanMasterMap().get(existingKey2));
                } else {
                    TreatmentOrPlanMaster treatmentOrPlanMaster = new TreatmentOrPlanMaster();
                    treatmentOrPlanMaster.setTreatmentOrPlan(text);
                    treatmentOrPlanMasterRepository.save(treatmentOrPlanMaster);
                    addCUIMapping(type, text);
                    return new MasterDataItem(treatmentOrPlanMaster.getId(), treatmentOrPlanMaster.getTreatmentOrPlan());
                }

            case "medications":
                Integer existingKey3 = codingDbUtility.getMedicationsMasterMap().entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equalsIgnoreCase(text))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

                if (existingKey3 != null) {
                    return new MasterDataItem(existingKey3, codingDbUtility.getMedicationsMasterMap().get(existingKey3));
                } else {
                    MedicationsMaster medicationsMaster = new MedicationsMaster();
                    medicationsMaster.setMedications(text);
                    medicationsMasterRepository.save(medicationsMaster);
                    addCUIMapping(type, text);
                    return new MasterDataItem(medicationsMaster.getId(), medicationsMaster.getMedications());
                }

            default:
                System.out.println("Type Miss match");
                break;
        }
        return new MasterDataItem();
    }

    private void addCUIMapping(String type, String text) {
        System.out.println("Jay Hind Dosto");
    }
}
