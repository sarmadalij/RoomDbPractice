package com.sarmadali.roomdbpractice.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "enrollment",
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "stdId",
                        childColumns = "studentId", //here which holds the foreign key
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Course.class,
                        parentColumns = "courseCode",
                        childColumns = "courseId", //here which holds the foreign key
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("studentId"), @Index("courseId")})
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    private int enrollId;
    @NonNull
    private String studentId;
    @NonNull
    private String courseId;
    @NonNull
    private String teacherId;
    //constructor
    public Enrollment(@NonNull String studentId, @NonNull String courseId, @NonNull String teacherId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }
    //getters and setters
    @NonNull
    public String getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(@NonNull String teacherId) {
        this.teacherId = teacherId;
    }
    public int getEnrollId() {
        return enrollId;
    }
    public void setEnrollId(int enrollId) {
        this.enrollId = enrollId;
    }
    @NonNull
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(@NonNull String studentId) {
        this.studentId = studentId;
    }
    @NonNull
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(@NonNull String courseId) {
        this.courseId = courseId;
    }
}
