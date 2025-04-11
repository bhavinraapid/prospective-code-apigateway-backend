package com.example.Redesign.response;

import java.util.List;
import java.util.Map;

public class CuiResponse {
    private String heading;
    private String term;
    private Map<String, List<String>> cuis;

    public CuiResponse() {
    }

    // Getters and setters

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Map<String, List<String>> getCuis() {
        return cuis;
    }

    public void setCuis(Map<String, List<String>> cuis) {
        this.cuis = cuis;
    }
}
