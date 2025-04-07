package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "physical_exam_master") // Table name as per your schema
public class PhysicalExamMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;  // Primary key with auto increment

    @Column(name = "physical_exam", nullable = false, unique = true, length = 125)
    private String physicalExam;  // Unique and non-nullable field

    // Default constructor
    public PhysicalExamMaster() {
    }

    // Constructor
    public PhysicalExamMaster(String physicalExam) {
        this.physicalExam = physicalExam;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhysicalExam() {
        return physicalExam;
    }

    public void setPhysicalExam(String physicalExam) {
        this.physicalExam = physicalExam;
    }

    // Optional: Override toString() method for easy debugging
    @Override
    public String toString() {
        return "PhysicalExamMaster{" +
                "id=" + id +
                ", physicalExam='" + physicalExam + '\'' +
                '}';
    }
}