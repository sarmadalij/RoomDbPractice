package com.sarmadali.roomdbpractice.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sarmadali.roomdbpractice.Entity.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void insertCourse(Course course);
    @Query("SELECT * FROM course")
    List<Course> getAllCourse();
}
