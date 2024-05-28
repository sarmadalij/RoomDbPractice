package com.sarmadali.roomdbpractice.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.sarmadali.roomdbpractice.Entity.Teacher;
import com.sarmadali.roomdbpractice.Repository.TeacherRepository;

import java.util.List;

public class TeacherViewModel extends AndroidViewModel {

    private TeacherRepository teacherRepository;
    private LiveData<List<Teacher>> teacherAllData;

    public TeacherViewModel(@NonNull Application application) {
        super(application);

        teacherRepository = new TeacherRepository(application);
        teacherAllData = teacherRepository.getTeacherAllData();
    }

    public LiveData<List<Teacher>> getTeacherAllData(){
        return teacherAllData;
    }

    public void insertTeacher(Teacher teacher){
        teacherRepository.insertTeacher(teacher);
    }
    public void deleteTeacher(Teacher teacher){
        teacherRepository.deleteTeacher(teacher);
    }

    public void updateTeacher(Teacher oldTeacher, Teacher newTeacher) {
        teacherRepository.updateTeacher(oldTeacher, newTeacher);
    }
}
