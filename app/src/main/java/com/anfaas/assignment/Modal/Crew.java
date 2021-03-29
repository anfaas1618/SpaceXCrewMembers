package com.anfaas.assignment.Modal;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crew",indices = @Index(value = {"crew_id"}, unique = true))
public class Crew {

    @PrimaryKey(autoGenerate = true)
    private int database_id;

    @SerializedName("id")
    private String crew_id;

    @SerializedName("name")
    private String crew_name;

    @SerializedName("image")
    private String poster;

    @SerializedName("status")
    private String status;

    @SerializedName("wikipedia")
    private String crew_description;

    public Crew(int database_id, String crew_id, String crew_name, String poster, String status, String crew_description) {
        this.database_id = database_id;
        this.crew_id = crew_id;
        this.crew_name = crew_name;
        this.poster = poster;
        this.status = status;
        this.crew_description = crew_description;
    }

    public int getDatabase_id() {
        return database_id;
    }

    public void setDatabase_id(int database_id) {
        this.database_id = database_id;
    }

    public String getCrew_id() {
        return crew_id;
    }

    public void setCrew_id(String crew_id) {
        this.crew_id = crew_id;
    }

    public String getCrew_name() {
        return crew_name;
    }

    public void setCrew_name(String crew_name) {
        this.crew_name = crew_name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCrew_description() {
        return crew_description;
    }

    public void setCrew_description(String crew_description) {
        this.crew_description = crew_description;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "id=" + database_id +
                ", crew_id=" + crew_id +
                ", crew_name='" + crew_name + '\'' +
                ", poster='" + poster + '\'' +
                ", status=" + status +  '\'' +
                ", wiki=" + crew_description+
                '}';
    }
}
