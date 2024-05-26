package com.sarmadali.roomdbpractice.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepository courseRepository;
    private LiveData<List<Course>> courseAllData;
    public CourseViewModel(@NonNull Application application) {
        super(application);

        courseRepository = new CourseRepository(application);
        courseAllData = courseRepository.getCourseAllData();
    }

    public LiveData<List<Course>> getCourseAllData(){
        return courseAllData;
    }

    public void insertCourse(Course course){
        courseRepository.insertCourse(course);
    }
}
