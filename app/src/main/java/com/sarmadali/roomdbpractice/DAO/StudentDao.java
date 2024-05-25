package com.sarmadali.roomdbpractice.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sarmadali.roomdbpractice.Entity.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student student);
    @Query("SELECT * FROM student")
    List<Student> getAllStudents();
}
