package com.example.Redesign.Controller;

import com.example.Redesign.response.CodeGroupResponse;
import com.example.Redesign.Model.CodeMaster;
import com.example.Redesign.response.CategoryDetails;
import com.example.Redesign.Service.MajorService;
import com.example.Redesign.Service.SuspectService;
import com.example.Redesign.request.GroupRequest;
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
            logger.info("Fetched category details for type: {}", type);
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
    public ResponseEntity<String> fetchCodeById(@PathVariable("codeId") Integer codeId) {
        try {
            String code = suspectService.fetchCodeById(codeId);
            return ResponseEntity.ok(code);
        } catch (Exception e) {
            logger.error("Error fetching client list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
