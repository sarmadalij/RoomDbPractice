package com.sarmadali.roomdbpractice.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teacher")
public class Teacher {

    public Teacher() {
    }


    @NonNull
    @PrimaryKey()
    private String teacherId;
    private String teacherName;
    private String teacherDept;

    public Teacher(@NonNull String teacherId, String teacherName, String teacherDept) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherDept = teacherDept;
    }
    //setters

    public void setTeacherId(@NonNull String teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTeacherDept(String teacherDept) {
        this.teacherDept = teacherDept;
    }

    //getters

    @NonNull
    public String getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherDept() {
        return teacherDept;
    }
}
