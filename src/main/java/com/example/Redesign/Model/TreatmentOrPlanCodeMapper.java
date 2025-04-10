package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "treatment_or_plan_code_mapper")
public class TreatmentOrPlanCodeMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code_id", nullable = false)
    private Integer codeId;

    @Column(name = "treatment_or_plan_id", nullable = false)
    private Integer treatmentOrPlanId;

    @Column(name = "is_major", nullable = false)
    private Boolean isMajor;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public Integer getTreatmentOrPlanId() {
        return treatmentOrPlanId;
    }

    public void setTreatmentOrPlanId(Integer treatmentOrPlanId) {
        this.treatmentOrPlanId = treatmentOrPlanId;
    }

    public Boolean getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor = isMajor;
    }

    @Override
    public String toString() {
        return "TreatmentOrPlanCodeMapper{" +
                "id=" + id +
                ", codeId=" + codeId +
                ", treatmentOrPlanId=" + treatmentOrPlanId +
                ", isMajor=" + isMajor +
                '}';
    }
}