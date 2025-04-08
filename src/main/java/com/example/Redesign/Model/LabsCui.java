package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "labs_cui")
public class LabsCui {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "labs_id", nullable = false)
    private int labsId;

    @Column(name = "CUI", length = 9, nullable = false)
    private String cui;

    @Column(name = "cui_type", nullable = false)
    private int cuiType;

    public LabsCui() {
    }

    public LabsCui(int labsId, String cui, int cuiType) {
        this.labsId = labsId;
        this.cui = cui;
        this.cuiType = cuiType;
    }

    public LabsCui(int id, int labsId, String cui, int cuiType) {
        this.id = id;
        this.labsId = labsId;
        this.cui = cui;
        this.cuiType = cuiType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabsId() {
        return labsId;
    }

    public void setLabsId(int labsId) {
        this.labsId = labsId;
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
        return "LabsCui{" +
                "id=" + id +
                ", labsId=" + labsId +
                ", cui='" + cui + '\'' +
                ", cuiType=" + cuiType +
                '}';
    }
}