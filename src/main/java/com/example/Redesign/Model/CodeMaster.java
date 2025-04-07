package com.example.Redesign.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "code_master")
public class CodeMaster implements Serializable {
    private static final long serialVersionUID = 9873249866873273L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "Code")
    private String code;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
