package com.example.Redesign.request;

public class SelectedItem {
    private Integer id;
    private String text;
    private String sourceTable;
    private Integer frequency;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    // Getters and Setters
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

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    @Override
    public String toString() {
        return "SelectedItem{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", sourceTable='" + sourceTable + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
