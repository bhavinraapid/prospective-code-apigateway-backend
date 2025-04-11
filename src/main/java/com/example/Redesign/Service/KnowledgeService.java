package com.example.Redesign.Service;

import com.example.Redesign.DTO.MasterDataItem;
import com.example.Redesign.Model.*;
import com.example.Redesign.Repository.*;
import com.example.Redesign.request.*;
import com.example.Redesign.response.CodeMappingResponse;
import com.example.Redesign.response.CuiResponse;
import com.example.Redesign.response.TextToCUIResponse;
import com.example.Redesign.utility.CodingDbUtility;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KnowledgeService {

    @Value("${cui.api.url}")
    private String cuiApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientMasterRepository clientMasterRepository;

    @Autowired
    private MajorMasterRepository majorMasterRepository;

    @Autowired
    private CodingDbUtility codingDbUtility;

    @Autowired
    private LabsCuiRepository labsCuiRepository;
    @Autowired
    private TreatmentOrPlanCuiRepository treatmentOrPlanCuiRepository;
    @Autowired
    private PhysicalExamCuiRepository physicalExamCuiRepository;
    @Autowired
    private MedicationsCuiRepository medicationsCuiRepository;

    @Autowired
    private CodeMasterRepository codeMasterRepository;
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

    @Autowired
    private CuiTypeRepository cuiTypeRepository;
    private Map<String, Integer> cuiTypeMap = new HashMap<>();

    @PostConstruct
    void loadKnowledge()
    {
        List<CuiType> cuiTypeList = cuiTypeRepository.findAll();
        for(CuiType cuiType : cuiTypeList)
        {
            cuiTypeMap.put(cuiType.getType(), cuiType.getId());
        }

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

    public MasterDataItem addToMaster(String type, String term) {

        String text = term.strip().toLowerCase();
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
                }
                else {
                    // Find CUI for this Term
                    CuiResponse cuiResponse = fetchCUIforTerm("labs", text);
                    if(cuiResponse == null)
                    {
                        System.out.println("We are not able to fetch CUIs ");
                        return new MasterDataItem(-1,"Not Added ");
                    }

                    LabsMaster labsMaster = new LabsMaster();
                    labsMaster.setLabs(text);
                    labsMasterRepository.save(labsMaster);

                    if(cuiResponse.getCuis().get("direct").isEmpty())
                    {
                        LabsCui labsCui = new LabsCui();
                        labsCui.setCuiType(1);
                        labsCui.setCui("NotFound");
                        labsCui.setLabsId(labsMaster.getId());
                        labsCuiRepository.save(labsCui);
                        return new MasterDataItem(labsMaster.getId(), labsMaster.getLabs());
                    }

                    for(Map.Entry<String, List<String>> entry : cuiResponse.getCuis().entrySet())
                    {
                        Integer cui_type = cuiTypeMap.get(entry.getKey());
                        if(cui_type == null)
                            continue;

                        for(String cui : entry.getValue())
                        {
                            LabsCui labsCui = new LabsCui();
                            labsCui.setCuiType(cui_type);
                            labsCui.setCui(cui);
                            labsCui.setLabsId(labsMaster.getId());
                            labsCuiRepository.save(labsCui);
                        }
                    }

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
                }
                else {

                    // Find CUI for this Term
                    CuiResponse cuiResponse = fetchCUIforTerm("physical_exam", text);
                    if(cuiResponse == null)
                    {
                        System.out.println("We are not able to fetch CUIs ");
                        return new MasterDataItem(-1,"Not Added ");
                    }

                    PhysicalExamMaster physicalExamMaster = new PhysicalExamMaster();
                    physicalExamMaster.setPhysicalExam(text);
                    physicalExamMasterRepository.save(physicalExamMaster);

                    if(cuiResponse.getCuis().get("direct").isEmpty())
                    {
                        PhysicalExamCui physicalExamCui = new PhysicalExamCui();
                        physicalExamCui.setCui("NotFound");
                        physicalExamCui.setCuiType(1);
                        physicalExamCui.setPhysicalExamId(physicalExamMaster.getId());
                        physicalExamCuiRepository.save(physicalExamCui);
                        return new MasterDataItem(physicalExamMaster.getId(), physicalExamMaster.getPhysicalExam());
                    }
                    for(Map.Entry<String, List<String>> entry : cuiResponse.getCuis().entrySet())
                    {
                        Integer cui_type = cuiTypeMap.get(entry.getKey());
                        if(cui_type == null)
                            continue;

                        for(String cui : entry.getValue())
                        {
                            PhysicalExamCui physicalExamCui = new PhysicalExamCui();
                            physicalExamCui.setCui(cui);
                            physicalExamCui.setCuiType(cui_type);
                            physicalExamCui.setPhysicalExamId(physicalExamMaster.getId());
                            physicalExamCuiRepository.save(physicalExamCui);
                        }
                    }

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
                }
                else {
                    // Find CUI for this Term
                    CuiResponse cuiResponse = fetchCUIforTerm("treatment_or_plan", text);
                    if(cuiResponse == null)
                    {
                        System.out.println("We are not able to fetch CUIs ");
                        return new MasterDataItem(-1,"Not Added ");
                    }

                    TreatmentOrPlanMaster treatmentOrPlanMaster = new TreatmentOrPlanMaster();
                    treatmentOrPlanMaster.setTreatmentOrPlan(text);
                    treatmentOrPlanMasterRepository.save(treatmentOrPlanMaster);

                    if(cuiResponse.getCuis().get("direct").isEmpty())
                    {
                        TreatmentOrPlanCui treatmentOrPlanCui = new TreatmentOrPlanCui();
                        treatmentOrPlanCui.setCui("NotFound");
                        treatmentOrPlanCui.setCuiType(1);
                        treatmentOrPlanCui.setTreatmentOrPlanId(treatmentOrPlanMaster.getId());
                        treatmentOrPlanCuiRepository.save(treatmentOrPlanCui);
                        return new MasterDataItem(treatmentOrPlanMaster.getId(), treatmentOrPlanMaster.getTreatmentOrPlan());
                    }

                    for(Map.Entry<String, List<String>> entry : cuiResponse.getCuis().entrySet())
                    {
                        Integer cui_type = cuiTypeMap.get(entry.getKey());
                        if(cui_type == null)
                            continue;

                        for(String cui : entry.getValue())
                        {
                            TreatmentOrPlanCui treatmentOrPlanCui = new TreatmentOrPlanCui();
                            treatmentOrPlanCui.setCui(cui);
                            treatmentOrPlanCui.setCuiType(cui_type);
                            treatmentOrPlanCui.setTreatmentOrPlanId(treatmentOrPlanMaster.getId());
                            treatmentOrPlanCuiRepository.save(treatmentOrPlanCui);
                        }
                    }


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
                }
                else {
                    // Find CUI for this Term
                    CuiResponse cuiResponse = fetchCUIforTerm("medications", text);
                    if(cuiResponse == null)
                    {
                        System.out.println("We are not able to fetch CUIs ");
                        return new MasterDataItem(-1,"Not Added ");
                    }

                    MedicationsMaster medicationsMaster = new MedicationsMaster();
                    medicationsMaster.setMedications(text);
                    medicationsMasterRepository.save(medicationsMaster);

                    if(cuiResponse.getCuis().get("direct").isEmpty())
                    {
                        MedicationsCui medicationsCui = new MedicationsCui();
                        medicationsCui.setCui("NotFound");
                        medicationsCui.setCuiType(1);
                        medicationsCui.setMedicationsId(medicationsMaster.getId());
                        medicationsCuiRepository.save(medicationsCui);
                        return new MasterDataItem(medicationsMaster.getId(), medicationsMaster.getMedications());
                    }

                    for(Map.Entry<String, List<String>> entry : cuiResponse.getCuis().entrySet())
                    {
                        Integer cui_type = cuiTypeMap.get(entry.getKey());
                        if(cui_type == null)
                            continue;

                        for(String cui : entry.getValue())
                        {
                            MedicationsCui medicationsCui = new MedicationsCui();
                            medicationsCui.setCui(cui);
                            medicationsCui.setCuiType(cui_type);
                            medicationsCui.setMedicationsId(medicationsMaster.getId());
                            medicationsCuiRepository.save(medicationsCui);
                        }
                    }


                    return new MasterDataItem(medicationsMaster.getId(), medicationsMaster.getMedications());
                }

            default:
                System.out.println("Type Miss match");
                break;
        }
        return new MasterDataItem();
    }

    private CuiResponse fetchCUIforTerm(String type, String text) {

        CuiRequest cuiRequest = new CuiRequest(text, type);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<CuiRequest> entity = new HttpEntity<>(cuiRequest, headers);

        try {
            ResponseEntity<CuiResponse> response = restTemplate.postForEntity(cuiApiUrl, entity, CuiResponse.class);
            return response.getBody();
        } catch (Exception e) {
            // Optional: Log the error for debugging
            System.err.println("Error occurred while fetching CUI mapping: " + e.getMessage());
            return null;
        }
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

    public String deleteCodeMappingData(CodeMappingRequest codeMappingRequest) {

        Integer codeId = codeMappingRequest.getCodeMaster().getId();
        Integer masterId = codeMappingRequest.getMasterDataItem().getId();

        String message;

        try {
            switch (codeMappingRequest.getType()) {
                case "labs":
                    LabDataCodeMapper labDataCodeMapper = labDataCodeMapperRepository.findByCodeIdAndLabs(codeId, masterId);
                    if (labDataCodeMapper != null) {
                        labDataCodeMapperRepository.delete(labDataCodeMapper);
                    }
                    break;

                case "physicalExam":
                    System.out.println("We are here ");
                    PhysicalExamCodeMapper physicalExamCodeMapper = physicalExamCodeMapperRepository.findByCodeIdAndPhysicalExamId(codeId, masterId);
                    System.out.println("Get Data : "+physicalExamCodeMapper);
                    if (physicalExamCodeMapper != null) {
                        physicalExamCodeMapperRepository.delete(physicalExamCodeMapper);
                    }
                    break;

                case "treatment":
                    TreatmentOrPlanCodeMapper treatmentOrPlanCodeMapper = treatmentOrPlanCodeMapperRepository.findByCodeIdAndTreatmentOrPlanId(codeId, masterId);
                    if (treatmentOrPlanCodeMapper != null) {
                        treatmentOrPlanCodeMapperRepository.delete(treatmentOrPlanCodeMapper);
                    }
                    break;

                case "medications":
                    MedicationsCodeMapper medicationsCodeMapper = medicationsCodeMapperRepository.findByCodeIdAndMedicationsId(codeId, masterId);
                    if (medicationsCodeMapper != null) {
                        medicationsCodeMapperRepository.delete(medicationsCodeMapper);
                    }
                    break;

                default:
                    System.out.println("Type mismatch");
                    return "Invalid type";
            }

            message = "Deleted successfully";

        } catch (Exception e) {
            e.printStackTrace(); // Optional: log the error
            message = "Failed to delete mapping due to error: " + e.getMessage();
        }

        return message;
    }
}
