package com.example.Redesign.request;

import com.example.Redesign.DTO.MasterDataItem;
import com.example.Redesign.Model.CodeMaster;

public class Payload {
    private MasterDataItem masterDataItem;
    private CodeMaster codeMaster;
    private String relationship;
    private Float value1;
    private Float value2;
    private String unit;
    private String exceptValue;
    private String comment;

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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Float getValue1() {
        return value1;
    }

    public void setValue1(Float value1) {
        this.value1 = value1;
    }

    public Float getValue2() {
        return value2;
    }

    public void setValue2(Float value2) {
        this.value2 = value2;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getExceptValue() {
        return exceptValue;
    }

    public void setExceptValue(String exceptValue) {
        this.exceptValue = exceptValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "masterDataItem=" + masterDataItem +
                ", codeMaster=" + codeMaster +
                ", relationship='" + relationship + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                ", unit='" + unit + '\'' +
                ", exceptValue='" + exceptValue + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
