package com.example.Redesign.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "treatment_or_plan_cui")
public class TreatmentOrPlanCui {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "treatment_or_plan_id", nullable = false)
    private int treatmentOrPlanId;

    @Column(name = "CUI", length = 9, nullable = false)
    private String cui;

    @Column(name = "cui_type", nullable = false)
    private int cuiType;

    public TreatmentOrPlanCui() {
    }

    public TreatmentOrPlanCui(int treatmentOrPlanId, String cui, int cuiType) {
        this.treatmentOrPlanId = treatmentOrPlanId;
        this.cui = cui;
        this.cuiType = cuiType;
    }

    public TreatmentOrPlanCui(int id, int treatmentOrPlanId, String cui, int cuiType) {
        this.id = id;
        this.treatmentOrPlanId = treatmentOrPlanId;
        this.cui = cui;
        this.cuiType = cuiType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTreatmentOrPlanId() {
        return treatmentOrPlanId;
    }

    public void setTreatmentOrPlanId(int treatmentOrPlanId) {
        this.treatmentOrPlanId = treatmentOrPlanId;
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
        return "TreatmentOrPlanCui{" +
                "id=" + id +
                ", treatmentOrPlanId=" + treatmentOrPlanId +
                ", cui='" + cui + '\'' +
                ", cuiType=" + cuiType +
                '}';
    }
}