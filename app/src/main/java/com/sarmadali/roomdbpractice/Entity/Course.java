package com.sarmadali.roomdbpractice.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "course",
        foreignKeys = @ForeignKey(entity = Teacher.class,
                parentColumns = "teacherId",
                childColumns = "teacherId", //here which holds the foreign key
                onDelete = ForeignKey.CASCADE),
        indices = {@Index("teacherId")})
public class Course {

    public Course() {
    }
    @NonNull
    @PrimaryKey()
    private String courseCode;
    private String courseName;
    private int creditHours;
    private String teacherId;

    //constructors
    public Course(String courseCode, String courseName, int creditHours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }
    public Course(@NonNull String courseCode, String courseName, int creditHours, String teacherId) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.teacherId = teacherId;
    }
    //setters
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    //getters
    public String getCourseCode() {
        return courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public int getCreditHours() {
        return creditHours;
    }
    public String getTeacherId() {
        return teacherId;
    }
}
