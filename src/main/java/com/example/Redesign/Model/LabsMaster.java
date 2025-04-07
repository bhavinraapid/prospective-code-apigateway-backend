package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "labs_master") // Table name as per your schema
public class LabsMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;  // Primary key with auto increment

    @Column(name = "labs", nullable = false, unique = true, length = 125)
    private String labs;  // Unique and non-nullable field

    // Default constructor
    public LabsMaster() {
    }

    // Constructor
    public LabsMaster(String labs) {
        this.labs = labs;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabs() {
        return labs;
    }

    public void setLabs(String labs) {
        this.labs = labs;
    }

    // Optional: Override toString() method
    @Override
    public String toString() {
        return "LabsMaster{" +
                "id=" + id +
                ", labs='" + labs + '\'' +
                '}';
    }
}