package com.example.Redesign.Service;

import com.example.Redesign.response.CodeGroupResponse;
import com.example.Redesign.DTO.MajorMasterDTO;
import com.example.Redesign.Model.MajorMaster;
import com.example.Redesign.Repository.*;
import com.example.Redesign.request.GroupRequest;
import com.example.Redesign.request.SelectedItem;
import com.example.Redesign.utility.CodingDbUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MajorService {

    private static final Logger logger = LoggerFactory.getLogger(MajorService.class);

    @Autowired
    private MajorMasterRepository majorMasterRepository;

    @Autowired
    private CodingDbUtility codingDbUtility;

    private Map<Integer, String> labsMasterMap = new HashMap<>();
    private Map<Integer, String> physicalExamMasterMap = new HashMap<>();
    private Map<Integer, String> medicationsMasterMap = new HashMap<>();
    private  Map<Integer, String> treatmentOrPlanMasterMap = new HashMap<>();
    private  Map<Integer, String> codeMasterMap = new HashMap<>();


    public List<CodeGroupResponse> getGroupsForCodeId(int codeId)
    {
        try {
            List<CodeGroupResponse> codeGroupResponseList = new ArrayList<>();

            List<MajorMaster> majorMasterList = majorMasterRepository.findByCodeId(codeId);
            if (majorMasterList.isEmpty())
                return new ArrayList<>();

            Map<Integer, List<MajorMasterDTO>> groupedData = majorMasterList.stream()
                    .collect(Collectors.groupingBy(
                            MajorMaster::getGroupId,
                            Collectors.mapping(
                                    m -> new MajorMasterDTO(
                                            m.getId(),
                                            m.getGroupId(),
                                            m.getCategoryType(),
                                            m.getCategoryId(),
                                            m.getCodeId(),
                                            m.getFrequency(),
                                            getCategoryText(m.getCategoryType(), m.getCategoryId()),
                                            m.getClient()
                                    ),
                                    Collectors.toList()
                            )
                    ));

            for (Map.Entry<Integer, List<MajorMasterDTO>> entry : groupedData.entrySet()) {
                String groupClient = entry.getValue().getFirst().getClient();
                codeGroupResponseList.add(new CodeGroupResponse(entry.getKey(), entry.getValue(), groupClient));
            }
            return codeGroupResponseList;
        }
        catch (Exception e) {
            return Collections.emptyList();
        }

    }

    public String getCategoryText(String categoryType, Integer categoryId)
    {
        this.labsMasterMap = codingDbUtility.getLabsMasterMap();
        this.treatmentOrPlanMasterMap = codingDbUtility.getTreatmentOrPlanMasterMap();
        this.medicationsMasterMap = codingDbUtility.getMedicationsMasterMap();
        this.physicalExamMasterMap = codingDbUtility.getPhysicalExamMasterMap();
        this.codeMasterMap = codingDbUtility.getCodeMasterMap();

        String ans;
        switch (categoryType)
        {
            case "C01":
                ans = labsMasterMap.get(categoryId);
                break;
            case "C02":
                ans = treatmentOrPlanMasterMap.get(categoryId);
                break;
            case "C03":
                ans = physicalExamMasterMap.get(categoryId);
                break;
            case "C04":
                ans = medicationsMasterMap.get(categoryId);
                break;
            case "C05":
                ans = codeMasterMap.get(categoryId);
                break;
            default:
                ans = "Not Valid Id";
                break;

        }
        return ans;
    }

    public void deleteGroup(int codeId, int groupId) {

        try {
            majorMasterRepository.deleteByCodeIdAndGroupId(codeId, groupId);
            logger.info("Deleted group with Group ID: {} and Code ID: {}", groupId, codeId);
        } catch (Exception e) {
            logger.error("Error deleting group with Group ID: {} and Code ID: {}", groupId, codeId, e);
        }

    }


    public String createMajorIndicatorsGroup(GroupRequest groupRequest) {
        try {
            if (groupRequest == null || groupRequest.getSelectedItems().isEmpty()) {
                return "Invalid request: No items selected";
            }

            Integer codeId = groupRequest.getCodeId();
            String client = Optional.ofNullable(groupRequest.getClient()).orElse("Unknown Client");

            Integer maxGroupId = majorMasterRepository.findMaxGroupId();
            if (maxGroupId == 0) {
                maxGroupId = 1001;
            } else {
                maxGroupId += 1;
            }

            System.out.println("Line Number 137 major Service : "+maxGroupId);
            for (SelectedItem selectedItem : groupRequest.getSelectedItems()) {
                if (selectedItem != null && selectedItem.getSourceTable() != null && selectedItem.getId() != null) {
                    createMajorMasterEntry(selectedItem, codeId, maxGroupId, client);
                } else {
                    logger.warn("Skipping invalid entry: {}", selectedItem);
                }
            }

            return "Group Added Successfully";

        } catch (Exception e) {
            logger.error("Error creating major indicators group", e);
            return "Error while adding group";
        }
    }

    private void createMajorMasterEntry(SelectedItem selectedItem, Integer codeId, Integer groupId, String client) {
        String categoryType = switch (selectedItem.getSourceTable()) {
            case "labs" -> "C01";
            case "treatment" -> "C02";
            case "physicalExam" -> "C03";
            case "medications" -> "C04";
            case "mustRequiredCondition" -> "C05";
            default -> null;
        };

        if (categoryType != null) {
            MajorMaster majorMaster = new MajorMaster();
            majorMaster.setGroupId(groupId);
            majorMaster.setCategoryId(selectedItem.getId());
            majorMaster.setCategoryType(categoryType);
            majorMaster.setFrequency(Optional.of(selectedItem.getFrequency()).orElse(1));
            majorMaster.setCodeId(codeId);
            majorMaster.setClient(client);

            try {
                majorMasterRepository.save(majorMaster);
//                logger.info("Saved MajorMaster entry: {}", majorMaster);
            } catch (Exception e) {
                logger.error("Error saving MajorMaster entry: {}", majorMaster, e);
            }
        } else {
            logger.warn("Invalid sourceTable '{}' for selectedItem: {}", selectedItem.getSourceTable(), selectedItem);
        }
    }
}
