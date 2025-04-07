package com.example.Redesign.Service;

import com.example.Redesign.Model.ClientMaster;
import com.example.Redesign.Model.CodeMaster;
import com.example.Redesign.Repository.*;
import com.example.Redesign.response.CategoryDetails;
import com.example.Redesign.utility.CodingDbUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SuspectService {

    private static final Logger logger = LoggerFactory.getLogger(SuspectService.class);

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

    public List<CodeMaster> getCodeMasters() {
        try {
            List<CodeMaster> codeMasters = Optional.ofNullable(codeMasterRepository.findAll()).orElse(Collections.emptyList());
            if (codeMasters.isEmpty()) {
                logger.info("No code masters found.");
            }
            return codeMasters;
        } catch (Exception e) {
            logger.error("Error fetching code masters", e);
            return Collections.emptyList();
        }
    }

    public List<CategoryDetails> getCategoryDetailsForCode(int codeId, String type) {
        List<CategoryDetails> categoryDetails = Collections.emptyList();

        try {
            if (type == null || type.isBlank()) {
                logger.warn("Invalid type parameter provided: {}", type);
                return categoryDetails;
            }

            switch (type) {
                case "labs":
                    categoryDetails = Optional.ofNullable(labsMasterRepository.findCategoryMappingsForLabs(codeId))
                            .orElse(Collections.emptyList());
                    break;
                case "physicalExam":
                    categoryDetails = Optional.ofNullable(physicalExamMasterRepository.findCategoryMappingsForPhysicalExam(codeId))
                            .orElse(Collections.emptyList());
                    break;
                case "treatment":
                    categoryDetails = Optional.ofNullable(treatmentOrPlanMasterRepository.findCategoryMappingsForTreatmentOrPlan(codeId))
                            .orElse(Collections.emptyList());
                    break;
                case "medications":
                    categoryDetails = Optional.ofNullable(medicationsMasterRepository.findCategoryMappingsForMedications(codeId))
                            .orElse(Collections.emptyList());
                    break;
                case "mustRequiredCondition":
                    categoryDetails = Optional.ofNullable(codeMasterRepository.findCategoryMappingsForMustRequireCode())
                            .orElse(Collections.emptyList());
                    break;
                default:
                    logger.warn("Invalid category type: {}", type);
                    break;
            }
        } catch (Exception e) {
            logger.error("Error fetching category details for Code ID: {} and Type: {}", codeId, type, e);
        }

        return categoryDetails;
    }

    public List<String> getAllClients() {
        List<String> clients = Collections.emptyList();

        try {
            List<ClientMaster> clientMasterList = Optional.of(clientMasterRepository.findAll())
                    .orElse(Collections.emptyList());

            if (clientMasterList.isEmpty()) {
                logger.info("No clients found.");
            } else {
                clients = clientMasterList.stream()
                        .map(ClientMaster::getClientName)
                        .filter(clientName -> clientName != null && !clientName.isBlank())
                        .toList();
            }
        } catch (Exception e) {
            logger.error("Error fetching client names", e);
        }

        return clients;
    }

    public String fetchCodeById(Integer id) {

        return String.valueOf(codeMasterRepository.findById(id).get());
    }
}
