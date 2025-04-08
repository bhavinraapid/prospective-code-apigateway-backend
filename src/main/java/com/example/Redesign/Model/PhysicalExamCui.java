package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "physical_exam_cui")
public class PhysicalExamCui {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "physical_exam_id", nullable = false)
    private int physicalExamId;

    @Column(name = "CUI", length = 9, nullable = false)
    private String cui;

    @Column(name = "cui_type", nullable = false)
    private int cuiType;

    public PhysicalExamCui() {
    }

    public PhysicalExamCui(int physicalExamId, String cui, int cuiType) {
        this.physicalExamId = physicalExamId;
        this.cui = cui;
        this.cuiType = cuiType;
    }

    public PhysicalExamCui(int id, int physicalExamId, String cui, int cuiType) {
        this.id = id;
        this.physicalExamId = physicalExamId;
        this.cui = cui;
        this.cuiType = cuiType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhysicalExamId() {
        return physicalExamId;
    }

    public void setPhysicalExamId(int physicalExamId) {
        this.physicalExamId = physicalExamId;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public int getCuiType() {
        return cuiType;
    }

    public void setCuiType(int cuiType) {
        this.cuiType = cuiType;
    }

    @Override
    public String toString() {
        return "PhysicalExamCui{" +
                "id=" + id +
                ", physicalExamId=" + physicalExamId +
                ", cui='" + cui + '\'' +
                ", cuiType=" + cuiType +
                '}';
    }
}