package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "medications_code_mapper")
public class MedicationsCodeMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_id", nullable = false)
    private Long codeId;

    @Column(name = "medications_id")
    private Long medicationsId;

    @Column(name = "is_major")
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

    public Long getMedicationsId() {
        return medicationsId;
    }

    public void setMedicationsId(Long medicationsId) {
        this.medicationsId = medicationsId;
    }

    public Boolean getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor = isMajor;
    }
}