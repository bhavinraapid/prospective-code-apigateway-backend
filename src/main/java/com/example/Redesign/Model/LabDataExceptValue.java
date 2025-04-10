package com.example.Redesign.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "labdata_except_values")
public class LabDataExceptValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value", nullable = false, unique = true, length = 125)
    private String value;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
