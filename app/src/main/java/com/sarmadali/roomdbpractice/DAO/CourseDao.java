package com.sarmadali.roomdbpractice.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sarmadali.roomdbpractice.Entity.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void insertCourse(Course course);
    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAllCourse();

    //delete item
    @Delete
    void deleteCourse(Course course);

}
