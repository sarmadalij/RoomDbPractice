package com.sarmadali.roomdbpractice.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class EnrollmentWithDetails {
    @Embedded
    public Enrollment enrollment;

    @Relation(
            parentColumn = "courseId", //column in enrollment table that holds the foreign key
            entityColumn = "courseCode" //column in course table that is being referenced here
    )
    public Course course;

    @Relation(
            parentColumn = "studentId",
            entityColumn = "stdId"
    )
    public Student student;

    @Relation(
            parentColumn = "teacherId",
            entityColumn = "teacherId",
            entity = Teacher.class
    )
    public Teacher teacher;
}
