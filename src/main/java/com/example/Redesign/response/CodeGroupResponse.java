package com.example.Redesign.response;

import com.example.Redesign.DTO.MajorMasterDTO;

import java.util.List;

public class CodeGroupResponse {
    private int groupId;
    private String client;
    private List<MajorMasterDTO> majorMasters;

    public CodeGroupResponse(int groupId, List<MajorMasterDTO> majorMasters, String client) {
        this.groupId = groupId;
        this.majorMasters = majorMasters;
        this.client = client;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<MajorMasterDTO> getMajorMasters() {
        return majorMasters;
    }

    public void setMajorMasters(List<MajorMasterDTO> majorMasters) {
        this.majorMasters = majorMasters;
    }

    @Override
    public String toString() {
        return "CodeGroupResponse{" +
                "groupId=" + groupId +
                ", client='" + client + '\'' +
                ", majorMasters=" + majorMasters +
                '}';
    }
}
