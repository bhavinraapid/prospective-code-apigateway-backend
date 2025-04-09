package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Units {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unit", nullable = false, unique = true, length = 50)
    private String unit;

    // Constructors
    public Units() {
    }

    public Units(Integer id, String unit) {
        this.id = id;
        this.unit = unit;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // toString
    @Override
    public String toString() {
        return "Units{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                '}';
    }
}