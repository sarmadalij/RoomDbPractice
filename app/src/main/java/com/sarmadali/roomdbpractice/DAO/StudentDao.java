package com.sarmadali.roomdbpractice.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sarmadali.roomdbpractice.Entity.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student student);

    @Query("SELECT * FROM student")
    LiveData<List<Student>> getAllStudents();

    @Delete
    void deleteStudent(Student student);
}
