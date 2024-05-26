package com.sarmadali.roomdbpractice.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sarmadali.roomdbpractice.DAO.CourseDao;
import com.sarmadali.roomdbpractice.Database.DatabaseApp;
import com.sarmadali.roomdbpractice.Entity.Course;

import java.util.List;

public class CourseRepository {

    private CourseDao courseDao;
    private LiveData<List<Course>> courseAllData;

    public CourseRepository(Application application){

        DatabaseApp appDb = DatabaseApp.getDatabase(application);
        courseDao = appDb.courseDao();
        courseAllData = courseDao.getAllCourse();
    }

    public LiveData<List<Course>> getCourseAllData(){
        return courseAllData;
    }

    public void insertCourse(Course course){
        DatabaseApp.databaseWriteExecutor.execute(()->{
            courseDao.insertCourse(course);
        });
    }
}
