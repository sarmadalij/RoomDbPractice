package com.sarmadali.roomdbpractice.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sarmadali.roomdbpractice.DAO.TeacherDao;
import com.sarmadali.roomdbpractice.Database.DatabaseApp;
import com.sarmadali.roomdbpractice.Entity.Teacher;

import java.util.List;

public class TeacherRepository {

    private TeacherDao teacherDao;
    private LiveData<List<Teacher>> teacherAllData;

    public TeacherRepository(Application application){

        DatabaseApp appDb = DatabaseApp.getDatabase(application);
        teacherDao = appDb.teacherDao();
        teacherAllData = teacherDao.getAllTeachers();
    }

    public LiveData<List<Teacher>> getTeacherAllData(){
        return teacherAllData;
    }

    public void insertTeacher(Teacher teacher){
        DatabaseApp.databaseWriteExecutor.execute(()->{
            teacherDao.insertTeacher(teacher);
        });
    }
}
