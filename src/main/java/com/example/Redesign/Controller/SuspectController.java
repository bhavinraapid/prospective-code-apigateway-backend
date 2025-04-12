package com.example.Redesign.Controller;

import com.example.Redesign.DTO.MasterDataItem;
import com.example.Redesign.Service.KnowledgeService;
import com.example.Redesign.request.*;
import com.example.Redesign.response.CodeGroupResponse;
import com.example.Redesign.Model.CodeMaster;
import com.example.Redesign.response.CategoryDetails;
import com.example.Redesign.Service.MajorService;
import com.example.Redesign.Service.SuspectService;
import com.example.Redesign.response.CodeMappingResponse;
import com.example.Redesign.response.TextToCUIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RestController
@RequestMapping("/research")
@CrossOrigin(origins = "http://localhost:8080")
public class SuspectController {

    private static final Logger logger = LoggerFactory.getLogger(SuspectController.class);

    @Autowired
    private MajorService majorService;

    @Autowired
    private SuspectService suspectService;

    @Autowired
    private KnowledgeService knowledgeService;

    /**
     * Fetches all code masters
     */
    @GetMapping("/codes")
    public ResponseEntity<List<CodeMaster>> getCodes() {
        try {
            List<CodeMaster> codeMasters = suspectService.getCodeMasters();
            return ResponseEntity.ok(codeMasters);
        } catch (Exception e) {
            logger.error("Error fetching codes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    /**
     * Fetches category details for a given code and type
     */
    @GetMapping("/codes/{code}/{type}")
    public ResponseEntity<List<CategoryDetails>> getCategoryDetailsForCode(
            @PathVariable("code") int code,
            @PathVariable("type") String type) {
        try {
            List<CategoryDetails> categoryDetails = suspectService.getCategoryDetailsForCode(code, type);
//            logger.info("Fetched category details for type: {}", type);
            return ResponseEntity.ok(categoryDetails);
        } catch (Exception e) {
            logger.error("Error fetching category details for code: {} and type: {}", code, type, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    /**
     * Creates a new major indicators group
     */
    @PostMapping("/addGroup")
    public ResponseEntity<Map<String, String>> createMajorIndicatorsGroup(@RequestBody GroupRequest groupRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            logger.info("Received request to create major group: {}", groupRequest);
            String status = majorService.createMajorIndicatorsGroup(groupRequest);
            response.put("message", status);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error creating major indicators group", e);
            response.put("error", "Failed to create group");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Fetches groups for a given code ID
     */
    @GetMapping("/codes/groups/{codeId}")
    public ResponseEntity<List<CodeGroupResponse>> getGroupsForCodeId(@PathVariable("codeId") int codeId) {
        try {
            List<CodeGroupResponse> groups = majorService.getGroupsForCodeId(codeId);
            return ResponseEntity.ok(groups);
        } catch (Exception e) {
            logger.error("Error fetching groups for codeId: {}", codeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    /**
     * Deletes a specific group by codeId and groupId
     */
    @DeleteMapping("/codes/groups/delete/{codeId}/{groupId}")
    public ResponseEntity<Map<String, String>> deleteCodeGroup(
            @PathVariable int codeId,
            @PathVariable int groupId) {
        Map<String, String> response = new HashMap<>();
        try {
            majorService.deleteGroup(codeId, groupId);
            response.put("success", "true");
            response.put("message", "Group deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error deleting group with codeId: {} and groupId: {}", codeId, groupId, e);
            response.put("failed", "false");
            response.put("error", "Failed to delete group");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Fetches all available clients
     */
    @GetMapping("/clients")
    public ResponseEntity<List<String>> getAllClients() {
        try {
            List<String> clients = suspectService.getAllClients();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            logger.error("Error fetching client list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/codes/{codeId}")
    public ResponseEntity<CodeMaster> fetchCodeById(@PathVariable("codeId") Integer codeId) {
//        System.out.println("We are here : "+codeId);
        try {
            CodeMaster code = suspectService.fetchCodeById(codeId);
//            System.out.println(code);
            return ResponseEntity.ok(code);
        } catch (Exception e) {
            logger.error("Error fetching client list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/master-items/{type}")
    public ResponseEntity<List<MasterDataItem>> fetchMasterItems(@PathVariable("type") String type) {
        try {
            List<MasterDataItem> masterDataItemList = knowledgeService.fetchMasterItems(type);

            return ResponseEntity.ok(masterDataItemList);
        } catch (Exception e) {
            logger.error("Error fetching client list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


    @PostMapping("/codes/addCode")
    public ResponseEntity<String> addToCodeMaster(@RequestBody CodeRequest codeRequest) {
        String codeText = codeRequest.getText();
        if (codeText == null || codeText.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Code text cannot be empty");
        }

        String msg = knowledgeService.addToCodeMaster(codeText);


        return ResponseEntity.ok(msg);
    }


    @PostMapping("/master/add")
    public ResponseEntity<MasterDataItem> addMasterValue(@RequestBody MasterValueRequest request) {
        String type = request.getType();
        String text = request.getText();
//        System.out.println(request);
        if (type == null || type.trim().isEmpty() || text == null || text.trim().isEmpty()) {
            return ResponseEntity.ok(new MasterDataItem(-1,"")); // No body in bad request
        }

        MasterDataItem masterDataItem = knowledgeService.addToMaster(type, text.trim().toLowerCase());
//        System.out.println(masterDataItem);
//        System.out.println("=================");
        return ResponseEntity.ok(masterDataItem);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMasterValue(@RequestBody DeleteItemRequest deleteItemRequest)
    {
//        System.out.println(deleteItemRequest.toString());
        return ResponseEntity.ok( "We should not delete from master taable");
    }


    @PostMapping("/fetch/text-to-cuis")
    public ResponseEntity<List<TextToCUIResponse>> fetchTextToCuis(@RequestBody TextToCUIRequest textToCUIRequest) {
//        System.out.println("Type: " + textToCUIRequest.getType());
//        System.out.println("Item: " + textToCUIRequest.getMasterDataItem());
        List<TextToCUIResponse>  textToCUIResponseList = knowledgeService.fetchTextToCuis(textToCUIRequest);
//        System.out.println("Response at Line 200 : "+textToCUIResponseList);
        return ResponseEntity.ok(textToCUIResponseList);
    }


    @PostMapping("/get/code-mapping-data")
    public List<CodeMappingResponse> getCodeMappingData(@RequestBody CodeMappingRequest codeMappingRequest) {
        List<CodeMappingResponse> codeMappingResponseList =  knowledgeService.fetchCodeMappingData(codeMappingRequest);

//        System.out.println("Response at Line 209 : "+codeMappingResponseList);

        return codeMappingResponseList;
    }


    @PostMapping("/delete/code-mapping-data")
    public String deleteCodeMappingData(@RequestBody CodeMappingRequest codeMappingRequest) {
        String codeMappingResponseList =  knowledgeService.deleteCodeMappingData(codeMappingRequest);

//        System.out.println("Response at Line 209 : "+codeMappingResponseList);

        return codeMappingResponseList;
    }


    @PostMapping("/add/add-code-mapping")
    public ResponseEntity<String> addCodeMappingCodeMapper(@RequestBody AddCodeMappingRequest addCodeMappingRequest) {

//        System.out.println("AddCodeMappingRequest : "+addCodeMappingRequest);

        String response = knowledgeService.addCodeMappingCodeMapper(addCodeMappingRequest);

        return ResponseEntity.ok("Mapping saved successfully");
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Backend is healthy ✅";
    }



}
