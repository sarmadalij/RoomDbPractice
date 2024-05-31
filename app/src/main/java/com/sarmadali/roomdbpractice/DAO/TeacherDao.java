package com.sarmadali.roomdbpractice.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sarmadali.roomdbpractice.Entity.Teacher;

import java.util.List;

@Dao
public interface TeacherDao {
    @Insert
    void insertTeacher(Teacher teacher);

    @Query("SELECT * FROM teacher")
    LiveData<List<Teacher>> getAllTeachers();

    @Delete
    void deleteTeacher(Teacher teacher);

    @Update
    void updateTeacher(Teacher teacher);

    @Query("SELECT COUNT(*) FROM teacher WHERE teacherId = :teacherId")
    LiveData<Integer> isTeacherExists(String teacherId);
}
