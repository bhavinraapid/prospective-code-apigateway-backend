package com.example.Redesign.request;


import com.example.Redesign.DTO.MasterDataItem;
import com.example.Redesign.Model.CodeMaster;

public class CodeMappingRequest {

    private MasterDataItem masterDataItem;
    private CodeMaster codeMaster;
    private String type;

    public CodeMappingRequest() {
    }

    public CodeMappingRequest(MasterDataItem masterDataItem, CodeMaster codeMaster, String type) {
        this.masterDataItem = masterDataItem;
        this.codeMaster = codeMaster;
        this.type = type;
    }

    public MasterDataItem getMasterDataItem() {
        return masterDataItem;
    }

    public void setMasterDataItem(MasterDataItem masterDataItem) {
        this.masterDataItem = masterDataItem;
    }

    public CodeMaster getCodeMaster() {
        return codeMaster;
    }

    public void setCodeMaster(CodeMaster codeMaster) {
        this.codeMaster = codeMaster;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}