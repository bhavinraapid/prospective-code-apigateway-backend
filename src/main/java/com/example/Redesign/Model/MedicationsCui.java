package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "medications_cui")
public class MedicationsCui {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "medications_id", nullable = false)
    private int medicationsId;

    @Column(name = "CUI", length = 9, nullable = false)
    private String cui;

    @Column(name = "cui_type", nullable = false)
    private int cuiType;

    public MedicationsCui() {
    }

    public MedicationsCui(int medicationsId, String cui, int cuiType) {
        this.medicationsId = medicationsId;
        this.cui = cui;
        this.cuiType = cuiType;
    }

    public MedicationsCui(int id, int medicationsId, String cui, int cuiType) {
        this.id = id;
        this.medicationsId = medicationsId;
        this.cui = cui;
        this.cuiType = cuiType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicationsId() {
        return medicationsId;
    }

    public void setMedicationsId(int medicationsId) {
        this.medicationsId = medicationsId;
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
        return "MedicationsCui{" +
                "id=" + id +
                ", medicationsId=" + medicationsId +
                ", cui='" + cui + '\'' +
                ", cuiType=" + cuiType +
                '}';
    }
}