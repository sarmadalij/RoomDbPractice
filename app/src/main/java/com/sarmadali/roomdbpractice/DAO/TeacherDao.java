package com.sarmadali.roomdbpractice.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sarmadali.roomdbpractice.Entity.Teacher;

import java.util.List;

@Dao
public interface TeacherDao {
    @Insert
    void insertTeacher(Teacher teacher);

    @Query("SELECT * FROM teacher")
    LiveData<List<Teacher>> getAllTeachers();

    //data deletion
    @Query("DELETE FROM teacher")
    void deleteAll();
}
