package com.example.Redesign.Service;

import com.example.Redesign.DTO.MasterDataItem;
import com.example.Redesign.Model.CodeMaster;
import com.example.Redesign.Repository.*;
import com.example.Redesign.utility.CodingDbUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        try{
            System.out.println(codeText.trim().toUpperCase());
            if(codingDbUtility.getCodeMasterMap().containsValue(codeText.toUpperCase()))
            {
                System.out.println("We Are here ");
                return "Code Already Exists in Code master";
            }
            CodeMaster code = new CodeMaster();
            code.setCode(codeText.trim().toUpperCase());
            codeMasterRepository.save(code);

            return "Code Saved SuccessFully";
        }
        catch (Exception e)
        {
            System.out.println("Error While Save to CodeMaster : "+e.getMessage());
            return "Code is Not Saved Succesfully";
        }
    }
}
