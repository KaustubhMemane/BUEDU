package com.kmema.android.buedu.networkClient;

import java.util.List;


public class CourseInfo {
    String information;
    String option_note;
    List<String> options;
    String note;
    String core_courses_note;
    List<String> core_courses;
    String courses_note;
    List<String> courses;
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getOption_note() {
        return option_note;
    }

    public void setOption_note(String option_note) {
        this.option_note = option_note;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCore_courses_note() {
        return core_courses_note;
    }

    public void setCore_courses_note(String core_courses_note) {
        this.core_courses_note = core_courses_note;
    }

    public List<String> getCore_courses() {
        return core_courses;
    }

    public void setCore_courses(List<String> core_courses) {
        this.core_courses = core_courses;
    }

    public String getCourses_note() {
        return courses_note;
    }

    public void setCourses_note(String courses_note) {
        this.courses_note = courses_note;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

}
