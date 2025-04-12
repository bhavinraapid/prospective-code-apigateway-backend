package com.example.Redesign.request;

public class DeleteItemRequest {
    private String type;
    private int id;
    private String text;

    // Default constructor
    public DeleteItemRequest() {
    }

    // Parameterized constructor
    public DeleteItemRequest(String type, int id, String text) {
        this.type = type;
        this.id = id;
        this.text = text;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // toString
    @Override
    public String toString() {
        return "DeleteItemRequest{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
