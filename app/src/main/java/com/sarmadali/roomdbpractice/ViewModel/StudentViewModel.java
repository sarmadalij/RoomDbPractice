package com.sarmadali.roomdbpractice.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.Repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;
    private final LiveData<List<Student>> studentAllData;
    //constructor
    public StudentViewModel(@NonNull Application application) {
        super(application);

        //initialize
        studentRepository = new StudentRepository(application);
        studentAllData = studentRepository.getStudentAllData();
    }

    public LiveData<List<Student>> getStudentAllData(){
        return studentAllData;
    }

    public void insertStudent(Student student){
        studentRepository.insertStudent(student);
    }

    public void deleteStudent(Student student){
        studentRepository.deleteStudent(student);
    }
}
