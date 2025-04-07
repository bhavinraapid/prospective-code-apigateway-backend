package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "medications_master") // Table name as per your schema
public class MedicationsMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;  // Primary key with auto increment

    @Column(name = "medications", nullable = false, unique = true, length = 125)
    private String medications;  // Unique and non-nullable field

    // Default constructor
    public MedicationsMaster() {
    }

    // Constructor
    public MedicationsMaster(String medications) {
        this.medications = medications;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    // Optional: Override toString() method for easy debugging
    @Override
    public String toString() {
        return "MedicationsMaster{" +
                "id=" + id +
                ", medications='" + medications + '\'' +
                '}';
    }
}