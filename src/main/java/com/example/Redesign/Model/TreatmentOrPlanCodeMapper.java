package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "treatment_or_plan_code_mapper", schema = "prospective_coding_db_redesign")
public class TreatmentOrPlanCodeMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_id", nullable = false)
    private Long codeId;

    @Column(name = "treatment_or_plan_id", nullable = false)
    private Long treatmentOrPlanId;

    @Column(name = "is_major", nullable = false)
    private Boolean isMajor;

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

    public Long getTreatmentOrPlanId() {
        return treatmentOrPlanId;
    }

    public void setTreatmentOrPlanId(Long treatmentOrPlanId) {
        this.treatmentOrPlanId = treatmentOrPlanId;
    }

    public Boolean getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor = isMajor;
    }
}