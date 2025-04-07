package com.example.Redesign.Model;

import jakarta.persistence.*;

@Entity(name="client_master")
public class ClientMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "clientName", nullable = false, length = 255)
    private String clientName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "ClientMaster{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}