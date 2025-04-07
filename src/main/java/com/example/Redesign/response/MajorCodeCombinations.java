package com.example.Redesign.response;

import com.example.Redesign.Model.MajorMaster;

import java.util.List;

public class MajorCodeCombinations {

    private Integer groupId;
    private List<MajorMaster> majorMasters;

    public MajorCodeCombinations(Integer groupId, List<MajorMaster> majorMasters) {
        this.groupId = groupId;
        this.majorMasters = majorMasters;
    }

    public MajorCodeCombinations() {
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<MajorMaster> getMajorMasters() {
        return majorMasters;
    }

    public void setMajorMasters(List<MajorMaster> majorMasters) {
        this.majorMasters = majorMasters;
    }

    @Override
    public String toString() {
        return "MajorCodeCombinations{" +
                "groupId=" + groupId +
                ", majorMasters=" + majorMasters +
                '}';
    }
}
