package com.example.Redesign.request;

public class AddCodeMappingRequest {

    private String type;
    private Payload payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "AddCodeMappingRequest{" +
                "type='" + type + '\'' +
                ", payload=" + payload +
                '}';
    }
}