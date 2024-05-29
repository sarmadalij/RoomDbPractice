package com.sarmadali.roomdbpractice.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sarmadali.roomdbpractice.DAO.StudentDao;
import com.sarmadali.roomdbpractice.Database.DatabaseApp;
import com.sarmadali.roomdbpractice.Entity.Student;

import java.util.List;

import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Dispatchers;

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

    public void updateStudent(Student oldStudent, Student newStudent) {
        DatabaseApp.databaseWriteExecutor.execute(() -> {
            // Delete the old Student with the old primary key
            studentDao.deleteStudent(oldStudent);

            // Insert the new Student with the updated primary key
            studentDao.insertStudent(newStudent);
        });
    }

    public LiveData<Student> loginStudent(String studentId, String studentPass){
        return studentDao.login(studentId, studentPass);
    }
}
