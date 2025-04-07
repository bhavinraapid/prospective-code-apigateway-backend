package com.example.Redesign.Model;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "major_master")
public class MajorMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "GroupID", nullable = false)
    private int groupId;

    @Column(name = "CategoryType", nullable = false)
    private String categoryType;

    @Column(name = "CategoryID", nullable = false)
    private int categoryId;

    @Column(name = "CodeId", nullable = false)
    private int codeId;

    @Column(name = "Frequency", nullable = false)
    private int frequency;

    @Column(name = "client", nullable = false, length = 255)
    private String client;

    public MajorMaster() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MajorMaster that = (MajorMaster) o;
        return id == that.id &&
                groupId == that.groupId &&
                categoryId == that.categoryId &&
                codeId == that.codeId &&
                frequency == that.frequency &&
                Objects.equals(categoryType, that.categoryType) &&
                Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupId, categoryType, categoryId, codeId, frequency, client);
    }

    @Override
    public String toString() {
        return "MajorMaster{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", categoryType='" + categoryType + '\'' +
                ", categoryId=" + categoryId +
                ", codeId=" + codeId +
                ", frequency=" + frequency +
                ", client='" + client + '\'' +
                '}';
    }
}
