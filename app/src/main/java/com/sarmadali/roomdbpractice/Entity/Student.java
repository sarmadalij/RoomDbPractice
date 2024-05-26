package com.sarmadali.roomdbpractice.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {

    public Student() {
    }

    @NonNull
    @PrimaryKey()
    private String stdId;
    private String stdName;
    private String stdDepartment;

    public Student(String stdId, @NonNull String stdName, String stdDepartment) {
        this.stdName = stdName;
        this.stdId = stdId;
        this.stdDepartment = stdDepartment;
    }

    //setters
    public void setStdName(String stdName) {
        this.stdName = stdName;
    }
    public void setStdId(@NonNull String stdId) {
        this.stdId = stdId;
    }
    public void setStdDepartment(String stdDepartment) {
        this.stdDepartment = stdDepartment;
    }
    //getters
    public String getStdName() {
        return stdName;
    }
    @NonNull
    public String getStdId() {
        return stdId;
    }
    public String getStdDepartment() {
        return stdDepartment;
    }
}
