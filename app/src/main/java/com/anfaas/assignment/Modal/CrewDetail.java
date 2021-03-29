package com.anfaas.assignment.Modal;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crew_detail",indices = @Index(value = {"crew_detail_id","database_crew_detail_id"}, unique = true))
public class CrewDetail {
    @PrimaryKey(autoGenerate = true)
    private int database_crew_detail_id;

    @SerializedName("id")
    private String crew_detail_id;

    @SerializedName("name")
    private String crew_name;

    @SerializedName("image")
    private String poster_name;

    @SerializedName("status")
    private String crew_status;

    @SerializedName("wikipedia")
    private String crew_description;

    public CrewDetail(int database_crew_detail_id, String crew_detail_id, String crew_name, String poster_name, String crew_status, String crew_description) {
        this.database_crew_detail_id = database_crew_detail_id;
        this.crew_detail_id = crew_detail_id;
        this.crew_name = crew_name;
        this.poster_name = poster_name;
        this.crew_status = crew_status;
        this.crew_description = crew_description;
    }

    public int getDatabase_crew_detail_id() {
        return database_crew_detail_id;
    }

    public void setDatabase_crew_detail_id(int database_crew_detail_id) {
        this.database_crew_detail_id = database_crew_detail_id;
    }

    public String getCrew_detail_id() {
        return crew_detail_id;
    }

    public void setCrew_detail_id(String crew_detail_id) {
        this.crew_detail_id = crew_detail_id;
    }

    public String getCrew_name() {
        return crew_name;
    }

    public void setCrew_name(String crew_name) {
        this.crew_name = crew_name;
    }

    public String getPoster_name() {
        return poster_name;
    }

    public void setPoster_name(String poster_name) {
        this.poster_name = poster_name;
    }

    public String getCrew_status() {
        return crew_status;
    }

    public void setCrew_status(String crew_status) {
        this.crew_status = crew_status;
    }

    public String getCrew_description() {
        return crew_description;
    }

    public void setCrew_description(String crew_description) {
        this.crew_description = crew_description;
    }

    @Override
    public String toString() {
        return "CrewDetail{" +
                "id=" + database_crew_detail_id+
                ", crew_detail_id=" + crew_detail_id +
                ", crew_name='" + crew_name + '\'' +
                ", poster_name='" + poster_name + '\'' +
                ", crew_status=" + crew_status +
                ", crew_description='" + crew_description + '\'' +
                '}';
    }
}
