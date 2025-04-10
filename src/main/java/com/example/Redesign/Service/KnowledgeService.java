package com.example.Redesign.Service;

import com.example.Redesign.DTO.MasterDataItem;
import com.example.Redesign.Model.*;
import com.example.Redesign.Repository.*;
import com.example.Redesign.request.AddCodeMappingRequest;
import com.example.Redesign.request.CodeMappingRequest;
import com.example.Redesign.request.Payload;
import com.example.Redesign.request.TextToCUIRequest;
import com.example.Redesign.response.CodeMappingResponse;
import com.example.Redesign.response.TextToCUIResponse;
import com.example.Redesign.utility.CodingDbUtility;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private LabDataCodeMapperRepository labDataCodeMapperRepository;

    @Autowired
    private PhysicalExamCodeMapperRepository physicalExamCodeMapperRepository;

    @Autowired
    private TreatmentOrPlanCodeMapperRepository treatmentOrPlanCodeMapperRepository;

    @Autowired
    private MedicationsCodeMapperRepository medicationsCodeMapperRepository;

    @Autowired
    private LabDataExceptValueRepository labDataExceptValueRepository;
    Map<Integer, String> labDataExceptValuemap = new HashMap<>();

    @Autowired
    private UnitsRepository unitsRepository;



    @PostConstruct
    void loadKnowledge()
    {


    }

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
            CodeMaster code = new CodeMaster();
            code.setCode(codeText.trim().toUpperCase());
            codeMasterRepository.save(code);
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

    public List<TextToCUIResponse> fetchTextToCuis(TextToCUIRequest textToCUIRequest) {

        List<TextToCUIResponse> textToCUIResponseList = new ArrayList<>();

        switch (textToCUIRequest.getType())
        {
            case "labs":
                textToCUIResponseList = labsMasterRepository.getLabsCUIsByMasterId(textToCUIRequest.getMasterDataItem().getId());
                 break;
            case "physicalExam":
                textToCUIResponseList = physicalExamMasterRepository.getPhysicalExamCUIsByMasterId(textToCUIRequest.getMasterDataItem().getId());
                break;
            case "treatment":
                textToCUIResponseList = treatmentOrPlanMasterRepository.getTreatmentOrPlanCUIsByMasterId(textToCUIRequest.getMasterDataItem().getId());
                break;
            case "medications":
                textToCUIResponseList = medicationsMasterRepository.getMedicationsCUIsByMasterId(textToCUIRequest.getMasterDataItem().getId());
                break;
            default:
                System.out.println("Type Miss match");
                break;
        }
        return textToCUIResponseList;
    }

    public List<CodeMappingResponse> fetchCodeMappingData(CodeMappingRequest codeMappingRequest) {

        List<CodeMappingResponse> codeMappingResponseList = new ArrayList<>();

        switch (codeMappingRequest.getType())
        {
            case "labs":
                codeMappingResponseList = labsMasterRepository.getLabsCodeMappingData(codeMappingRequest.getCodeMaster().getId(),codeMappingRequest.getMasterDataItem().getId());
                break;
            case "physicalExam":
                codeMappingResponseList = physicalExamMasterRepository.getPhysicalExamCodeMapping(codeMappingRequest.getCodeMaster().getId(),codeMappingRequest.getMasterDataItem().getId());
                break;
            case "treatment":
                codeMappingResponseList = treatmentOrPlanMasterRepository.getTreatmentPlanCodeMapping(codeMappingRequest.getCodeMaster().getId(),codeMappingRequest.getMasterDataItem().getId());
                break;
            case "medications":
                codeMappingResponseList = medicationsMasterRepository.getMedicationCodeMapping(codeMappingRequest.getCodeMaster().getId(),codeMappingRequest.getMasterDataItem().getId());
                break;
            default:
                System.out.println("Type Miss match");
                break;
        }
        return codeMappingResponseList;

    }

    public String addCodeMappingCodeMapper(AddCodeMappingRequest request) {
        Payload payload = request.getPayload();
        Integer codeId = payload.getCodeMaster().getId();
        Integer masterId = payload.getMasterDataItem().getId();

        if (codeId == null || masterId == null) return "Invalid Payload";

        switch (request.getType()) {
            case "labs":
                return handleLabsMapping(codeId, masterId, payload);

            case "physicalExam":
                return handlePhysicalExamMapping(codeId, masterId);

            case "treatment":
                return handleTreatmentMapping(codeId, masterId);

            case "medications":
                return handleMedicationsMapping(codeId, masterId);

            default:
                return "Type mismatch";
        }
    }

    private String handleLabsMapping(Integer codeId, Integer masterId, Payload payload) {
        if (labDataCodeMapperRepository.findByCodeIdAndLabs(codeId, masterId) != null) {
            System.out.println("Mapping already exists");
            return "Mapping already exists";
        }

        LabDataCodeMapper mapper = new LabDataCodeMapper();
        mapper.setCodeId(codeId);
        mapper.setLabs(masterId);
        mapper.setRelationship(payload.getRelationship());
        mapper.setIsMajor(false);

        if (payload.getValue1() != null) mapper.setValue1(payload.getValue1());
        if (payload.getValue2() != null) mapper.setValue2(payload.getValue2());

        // Handle except value
        Map<Integer, String> labDataExceptValuemap = new HashMap<>();
        List<LabDataExceptValue> values = labDataExceptValueRepository.findAll();
        for (LabDataExceptValue item : values) {
            labDataExceptValuemap.put(item.getId(), item.getValue().strip().toLowerCase());
        }
        if(payload.getExceptValue() != null && !payload.getExceptValue().strip().isEmpty()) {
            String normalizedExcept = payload.getExceptValue().strip().toLowerCase();

            if(labDataExceptValuemap.containsValue(normalizedExcept)) {
                for (Map.Entry<Integer, String> entry : labDataExceptValuemap.entrySet()) {
                    if (entry.getValue().equals(normalizedExcept)) {
                        mapper.setExceptValue(entry.getKey());
                        break;
                    }
                }
            } else {
                LabDataExceptValue labDataExceptValue = new LabDataExceptValue();
                labDataExceptValue.setValue(normalizedExcept);
                labDataExceptValueRepository.save(labDataExceptValue);
                mapper.setExceptValue(labDataExceptValue.getId());
            }
        }

        // Handle unit
        Map<Integer, String> unitsMap = new HashMap<>();
        List<Units> units = unitsRepository.findAll();
        for (Units item : units) {
            unitsMap.put(item.getId(), item.getUnit().strip().toLowerCase());
        }
        if(payload.getUnit() != null && !payload.getUnit().strip().isEmpty()) {
            String normalizedUnit = payload.getUnit().strip().toLowerCase();

            if(unitsMap.containsValue(normalizedUnit)) {
                for (Map.Entry<Integer, String> entry : unitsMap.entrySet()) {
                    if (entry.getValue().equals(normalizedUnit)) {
                        mapper.setUnit(entry.getKey());
                        break;
                    }
                }
            } else {
                Units unit = new Units();
                unit.setUnit(normalizedUnit);
                unitsRepository.save(unit);
                mapper.setUnit(unit.getId());
            }
        }

        labDataCodeMapperRepository.save(mapper);
        System.out.println("New Mapper is : "+mapper);
        return "Mapping saved successfully";
    }

    private String handlePhysicalExamMapping(Integer codeId, Integer masterId) {
        if (physicalExamCodeMapperRepository.findByCodeIdAndPhysicalExamId(codeId, masterId) != null) {
            System.out.println("Mapping already exists");
            return "Mapping already exists";
        }

        PhysicalExamCodeMapper mapper = new PhysicalExamCodeMapper();
        mapper.setCodeId(codeId);
        mapper.setPhysicalExamId(masterId);
        mapper.setAgeCategory("nan");
        mapper.setGenderCategory("nan");
        mapper.setIsMajor(false);


        physicalExamCodeMapperRepository.save(mapper);
        System.out.println("New Mapper is : "+mapper);
        return "Mapping saved successfully";
    }

    private String handleTreatmentMapping(Integer codeId, Integer masterId) {
        if (treatmentOrPlanCodeMapperRepository.findByCodeIdAndTreatmentOrPlanId(codeId, masterId) != null) {
            System.out.println("Mapping already exists");
            return "Mapping already exists";
        }

        TreatmentOrPlanCodeMapper mapper = new TreatmentOrPlanCodeMapper();
        mapper.setCodeId(codeId);
        mapper.setTreatmentOrPlanId(masterId);
        mapper.setIsMajor(false);

        treatmentOrPlanCodeMapperRepository.save(mapper);
        System.out.println("New Mapper is : "+mapper);
        return "Mapping saved successfully";
    }

    private String handleMedicationsMapping(Integer codeId, Integer masterId) {
        if (medicationsCodeMapperRepository.findByCodeIdAndMedicationsId(codeId, masterId) != null) {
            System.out.println("Mapping already exists");
            return "Mapping already exists";
        }

        MedicationsCodeMapper mapper = new MedicationsCodeMapper();
        mapper.setCodeId(codeId);
        mapper.setMedicationsId(masterId);
        mapper.setIsMajor(false);

        medicationsCodeMapperRepository.save(mapper);
        System.out.println("New Mapper is : "+mapper);
        return "Mapping saved successfully";
    }

}
