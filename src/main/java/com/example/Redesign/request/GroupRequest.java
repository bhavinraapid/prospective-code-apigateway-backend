package com.example.Redesign.request;

import java.util.List;

public class GroupRequest {

    private Integer codeId;
    private String client;
    private List<SelectedItem> selectedItems;

    public String getClient() {
        return client;
    }

    // Getters and Setters
    public Integer getCodeId() {
        return codeId;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public List<SelectedItem> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<SelectedItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    @Override
    public String toString() {
        return "GroupRequest{" +
                "codeId=" + codeId +
                ", client='" + client + '\'' +
                ", selectedItems=" + selectedItems +
                '}';
    }
}
