package com.example.Redesign.DTO;


public class MajorMasterDTO {
    private int id;
    private int groupId;
    private String categoryType;
    private int categoryId;
    private int codeId;
    private int frequency;
    private String text;
    private String client;

    public MajorMasterDTO(int id, int groupId, String categoryType, int categoryId, int codeId, int frequency, String text, String client) {
        this.id = id;
        this.groupId = groupId;
        this.categoryType = categoryType;
        this.categoryId = categoryId;
        this.codeId = codeId;
        this.frequency = frequency;
        this.text = text;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getCodeId() {
        return codeId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "MajorMasterDTO{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", categoryType='" + categoryType + '\'' +
                ", categoryId=" + categoryId +
                ", codeId=" + codeId +
                ", frequency=" + frequency +
                ", text='" + text + '\'' +
                ", client='" + client + '\'' +
                '}';
    }
}
