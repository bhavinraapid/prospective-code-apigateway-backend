package com.example.Redesign.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "cui_type")
public class CuiType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", unique = true, nullable = false, length = 50)
    private String type;

    public CuiType() {
    }

    public CuiType(String type) {
        this.type = type;
    }

    public CuiType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CuiType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}