package com.example.Redesign.response;

public class CategoryDetails {

    private Integer id;
    private String text;

    // Public constructor matching the query result
    public CategoryDetails(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    // Default constructor
    public CategoryDetails() {
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CategoryDetails{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
