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
    private LiveData<List<CourseWithTeacher>> coursesWithTeachers;
    public CourseViewModel(@NonNull Application application) {
        super(application);

        courseRepository = new CourseRepository(application);

        coursesWithTeachers = courseRepository.getCoursesWithTeachers();
    }
    public LiveData<List<CourseWithTeacher>> getCoursesWithTeachers() {
        return coursesWithTeachers;
    }
    public LiveData<CourseWithTeacher> getCourseWithTeacher(String courseCode) {
        return courseRepository.getCourseWithTeacher(courseCode);
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
    public LiveData<Integer> verifyTeacher(String teacherId) {
        return courseRepository.isTeacherExists(teacherId);
    }
}
