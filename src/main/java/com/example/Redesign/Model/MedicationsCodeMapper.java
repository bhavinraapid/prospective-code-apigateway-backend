package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "medications_code_mapper")
public class MedicationsCodeMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code_id", nullable = false)
    private Integer codeId;

    @Column(name = "medications_id")
    private Integer medicationsId;

    @Column(name = "is_major")
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

    public Integer getMedicationsId() {
        return medicationsId;
    }

    public void setMedicationsId(Integer medicationsId) {
        this.medicationsId = medicationsId;
    }

    public Boolean getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor = isMajor;
    }

    @Override
    public String toString() {
        return "MedicationsCodeMapper{" +
                "id=" + id +
                ", codeId=" + codeId +
                ", medicationsId=" + medicationsId +
                ", isMajor=" + isMajor +
                '}';
    }
}