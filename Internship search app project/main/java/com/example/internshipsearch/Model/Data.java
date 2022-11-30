package com.example.jobsearch.Model;

public class Data {

    String title;
    String description;
    String skills;
    String company;
    String duration;

    String id;
    String date;

    public Data() {

    }

    public Data(String title, String description, String skills, String company, String duration, String id, String date) {
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.company = company;
        this.duration = duration;
        this.id = id;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
