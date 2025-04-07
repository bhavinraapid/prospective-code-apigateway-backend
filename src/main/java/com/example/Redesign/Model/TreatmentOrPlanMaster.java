package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "treatment_or_plan_master") // Table name as per your schema
public class TreatmentOrPlanMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;  // Primary key with auto increment

    @Column(name = "treatment_or_plan", nullable = false, unique = true, length = 125)
    private String treatmentOrPlan;  // Unique and non-nullable field

    // Default constructor
    public TreatmentOrPlanMaster() {
    }

    // Constructor
    public TreatmentOrPlanMaster(String treatmentOrPlan) {
        this.treatmentOrPlan = treatmentOrPlan;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTreatmentOrPlan() {
        return treatmentOrPlan;
    }

    public void setTreatmentOrPlan(String treatmentOrPlan) {
        this.treatmentOrPlan = treatmentOrPlan;
    }

    // Optional: Override toString() method for easy debugging
    @Override
    public String toString() {
        return "TreatmentOrPlanMaster{" +
                "id=" + id +
                ", treatmentOrPlan='" + treatmentOrPlan + '\'' +
                '}';
    }
}