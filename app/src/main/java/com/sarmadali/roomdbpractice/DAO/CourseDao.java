package com.sarmadali.roomdbpractice.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.CourseWithTeacher;
import com.sarmadali.roomdbpractice.Entity.Teacher;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void insertCourse(Course course);

    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAllCourse(); //not being used currently

    //delete item
    @Delete
    void deleteCourse(Course course);
    @Update
    void updateCourse(Course course);


    //change
    @Query("SELECT * FROM course")
    LiveData<List<CourseWithTeacher>> getCoursesWithTeachers();

    @Insert
    void insertTeacher(Teacher teacher);

}
