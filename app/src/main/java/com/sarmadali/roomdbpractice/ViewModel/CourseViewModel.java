package com.sarmadali.roomdbpractice.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.CourseWithTeacher;
import com.sarmadali.roomdbpractice.Repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepository courseRepository;
    private LiveData<List<Course>> courseAllData;

    private LiveData<List<CourseWithTeacher>> coursesWithTeachers;
    public CourseViewModel(@NonNull Application application) {
        super(application);

        courseRepository = new CourseRepository(application);
        courseAllData = courseRepository.getCourseAllData();

        //change
        coursesWithTeachers = courseRepository.getCoursesWithTeachers();
    }

    //change
    public LiveData<List<CourseWithTeacher>> getCoursesWithTeachers() {
        return coursesWithTeachers;
    }
    //change end

    public LiveData<List<Course>> getCourseAllData(){
        return courseAllData;
    }

    public void insertCourse(Course course){
        courseRepository.insertCourse(course);
    }
    public void deleteCourse(Course course){
        courseRepository.deleteCourse(course);
    }

    public void updateCourse(Course oldCourse, Course newCourse) {
        courseRepository.updateCourse(oldCourse, newCourse);
    }
}
