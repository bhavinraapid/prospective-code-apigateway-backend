package com.example.Redesign.request;

public class CuiRequest {
    private String heading;
    private String term;

    public CuiRequest() {}

    public CuiRequest(String term, String heading) {
        this.heading = heading;
        this.term = term;
    }

    // Getters and Setters
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
}
