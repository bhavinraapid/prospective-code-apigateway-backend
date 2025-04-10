package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "physical_exam_code_mapper")
public class PhysicalExamCodeMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code_id", nullable = false)
    private Integer codeId;

    @Column(name = "physical_exam_id", nullable = false)
    private Integer physicalExamId;

    @Column(name = "is_major")
    private Boolean isMajor;

    @Column(name = "age_category")
    private String ageCategory;

    @Column(name = "gender_category")
    private String genderCategory;

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

    public Integer getPhysicalExamId() {
        return physicalExamId;
    }

    public void setPhysicalExamId(Integer physicalExamId) {
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

    @Override
    public String toString() {
        return "PhysicalExamCodeMapper{" +
                "id=" + id +
                ", codeId=" + codeId +
                ", physicalExamId=" + physicalExamId +
                ", isMajor=" + isMajor +
                ", ageCategory='" + ageCategory + '\'' +
                ", genderCategory='" + genderCategory + '\'' +
                '}';
    }
}