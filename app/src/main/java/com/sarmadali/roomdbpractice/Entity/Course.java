package com.sarmadali.roomdbpractice.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {

    public Course() {
    }

    @NonNull
    @PrimaryKey()
    private int courseCode;

    private String courseName;
    private int creditHours;

    public Course(int courseCode, String courseName, int creditHours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    //setters

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    //getters

    public int getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }
}
