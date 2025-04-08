package com.example.Redesign.request;


import com.example.Redesign.DTO.MasterDataItem;

public class TextToCUIRequest {

    private String type;
    private MasterDataItem item;

    public TextToCUIRequest() {
    }

    public TextToCUIRequest(String type, MasterDataItem item) {
        this.type = type;
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MasterDataItem getItem() {
        return item;
    }

    public void setItem(MasterDataItem item) {
        this.item = item;
    }
}
