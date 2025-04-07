package com.example.Redesign.DTO;

public class MasterDataItem {
    private Integer id;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MasterDataItem(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public MasterDataItem() {
    }

    @Override
    public String toString() {
        return "MasterDataItem{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
