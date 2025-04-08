package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "physical_exam_code_mapper")
public class PhysicalExamCodeMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_id", nullable = false)
    private Long codeId;

    @Column(name = "physical_exam_id", nullable = false)
    private Long physicalExamId;

    @Column(name = "is_major")
    private Boolean isMajor;

    @Column(name = "age_category")
    private String ageCategory;

    @Column(name = "gender_category")
    private String genderCategory;

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

    public Long getPhysicalExamId() {
        return physicalExamId;
    }

    public void setPhysicalExamId(Long physicalExamId) {
        this.physicalExamId = physicalExamId;
    }

    public Boolean getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor = isMajor;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getGenderCategory() {
        return genderCategory;
    }

    public void setGenderCategory(String genderCategory) {
        this.genderCategory = genderCategory;
    }
}