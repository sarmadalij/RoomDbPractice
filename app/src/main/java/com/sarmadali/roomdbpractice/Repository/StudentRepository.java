package com.sarmadali.roomdbpractice.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sarmadali.roomdbpractice.DAO.StudentDao;
import com.sarmadali.roomdbpractice.Database.DatabaseApp;
import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.Student;

import java.util.List;

public class StudentRepository {

    private StudentDao studentDao;
    private LiveData<List<Student>> studentAllData;

    public StudentRepository(Application application){

        DatabaseApp appDb = DatabaseApp.getDatabase(application);
        studentDao = appDb.studentDao();
        studentAllData = studentDao.getAllStudents();
    }

    public LiveData<List<Student>> getStudentAllData(){
        return studentAllData;
    }

    public void insertStudent(Student student){
        DatabaseApp.databaseWriteExecutor.execute(()->{
            studentDao.insertStudent(student);
        });
    }

    public void  deleteStudent(Student student){
        DatabaseApp.databaseWriteExecutor.execute(()->{
            studentDao.deleteStudent(student);
        });
    }
}
