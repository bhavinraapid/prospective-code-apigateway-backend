package com.example.Redesign.request;


import com.example.Redesign.DTO.MasterDataItem;

public class TextToCUIRequest {

    private String type;
    private MasterDataItem masterDataItem;

    public TextToCUIRequest() {
    }

    public TextToCUIRequest(String type, MasterDataItem item) {
        this.type = type;
        this.masterDataItem = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MasterDataItem getMasterDataItem() {
        return masterDataItem;
    }

    public void setMasterDataItem(MasterDataItem item) {
        this.masterDataItem = item;
    }
}
