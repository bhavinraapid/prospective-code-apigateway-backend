package com.example.Redesign.response;


public class TextToCUIResponse {

    private String cui;
    private String text;
    private String cui_type;
    private Integer id;

    public TextToCUIResponse() {
    }

    public TextToCUIResponse(String cui, String text, String cui_type, Integer id) {
        this.cui = cui;
        this.text = text;
        this.cui_type = cui_type;
        this.id = id;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCui_type() {
        return cui_type;
    }

    public void setCui_type(String cui_type) {
        this.cui_type = cui_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TextToCUIResponse{" +
                "cui='" + cui + '\'' +
                ", text='" + text + '\'' +
                ", cui_type='" + cui_type + '\'' +
                ", id=" + id +
                '}';
    }
}
