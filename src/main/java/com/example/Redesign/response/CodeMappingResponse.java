package com.example.Redesign.response;

public final class CodeMappingResponse {

    private String code;
    private String cui;
    private String text;
    private String cuiType;
    private String relationship;
    private String unit;
    private Float value1;
    private Float value2;
    private Integer codeId;
    private Integer masterId;

    // Default constructor
    public CodeMappingResponse() {
    }

    // Constructor matching the JPQL query parameters
    public CodeMappingResponse(String code, String cui, String text, String cuiType, String relationship,
                               String unit, Float value1, Float value2, Integer codeId, Integer masterId) {
        this.code = code;
        this.cui = cui;
        this.text = text;
        this.cuiType = cuiType;
        this.relationship = relationship;
        this.unit = unit;
        this.value1 = value1;
        this.value2 = value2;
        this.codeId = codeId;
        this.masterId = masterId;
    }

    // Constructor matching the JPQL query parameters
    public CodeMappingResponse(String code, String cui, String text, String cuiType, Integer codeId, Integer masterId) {
        this.code = code;
        this.cui = cui;
        this.text = text;
        this.cuiType = cuiType;
        this.codeId = codeId;
        this.masterId = masterId;
    }

    // Getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCuiType() {
        return cuiType;
    }

    public void setCuiType(String cuiType) {
        this.cuiType = cuiType;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    @Override
    public String toString() {
        return "CodeMappingResponse{" +
                "code='" + code + '\'' +
                ", cui='" + cui + '\'' +
                ", text='" + text + '\'' +
                ", cuiType='" + cuiType + '\'' +
                ", relationship='" + relationship + '\'' +
                ", unit='" + unit + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                ", codeId=" + codeId +
                ", masterId=" + masterId +
                '}';
    }
}
