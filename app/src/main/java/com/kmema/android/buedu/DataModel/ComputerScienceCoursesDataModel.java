package com.kmema.android.buedu.DataModel;


import java.io.Serializable;

public class ComputerScienceCoursesDataModel implements Serializable {

    private String name;
    private String section_name;
    private String description;
    private String associated_Term;
    private String registration_dates;
    private String levels;
    private String note;
    private String credit;
    private String type;
    private String professor;
    private String time;
    private String days;
    private String where;
    private String date_range;
    private String schedule_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssociated_Term() {
        return associated_Term;
    }

    public void setAssociated_Term(String associated_Term) {
        this.associated_Term = associated_Term;
    }

    public String getRegistration_dates() {
        return registration_dates;
    }

    public void setRegistration_dates(String registration_dates) {
        this.registration_dates = registration_dates;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getDate_range() {
        return date_range;
    }

    public void setDate_range(String date_range) {
        this.date_range = date_range;
    }

    public String getSchedule_type() {
        return schedule_type;
    }

    public void setSchedule_type(String schedule_type) {
        this.schedule_type = schedule_type;
    }
}



