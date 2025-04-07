package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "labdata_code_mapper", schema = "prospective_coding_db_redesign")
public class LabDataCodeMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_id", nullable = false)
    private Long codeId;

    @Column(name = "labs", nullable = false)
    private Long labs;

    @Column(name = "relationship", nullable = false, length = 10)
    private String relationship;

    @Column(name = "value1")
    private Float value1;

    @Column(name = "value2")
    private Float value2;

    @Column(name = "except_value")
    private Integer exceptValue;

    @Column(name = "unit")
    private Integer unit;

    @Column(name = "is_major")
    private Boolean isMajor;

    @Column(name = "comment", length = 100)
    private String comment;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public Long getLabs() {
        return labs;
    }

    public void setLabs(Long labs) {
        this.labs = labs;
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

    public Integer getExceptValue() {
        return exceptValue;
    }

    public void setExceptValue(Integer exceptValue) {
        this.exceptValue = exceptValue;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Boolean getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor = isMajor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}