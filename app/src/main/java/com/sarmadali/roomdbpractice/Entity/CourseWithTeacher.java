package com.sarmadali.roomdbpractice.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

//its container for combined data not an entity/table in DB
public class CourseWithTeacher {
    @Embedded               //to include another entity
    public Course course;

    @Relation(              // defining relationship
            parentColumn = "teacherId", //it is the column in course table, holds the foreign key
            entityColumn = "teacherId"  //it is the column in teacher table, reference by foreign key
    )
    public Teacher teacher;
}
