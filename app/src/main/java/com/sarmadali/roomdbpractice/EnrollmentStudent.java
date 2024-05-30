package com.sarmadali.roomdbpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.ViewModel.CourseViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityEnrollmentStudentBinding;

public class EnrollmentStudent extends AppCompatActivity {

    ActivityEnrollmentStudentBinding enrollmentStudentBinding;
    private CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enrollmentStudentBinding = ActivityEnrollmentStudentBinding.inflate(getLayoutInflater());
        setContentView(enrollmentStudentBinding.getRoot());

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        // Retrieve the Student object from the Intent
        Intent intent = getIntent();
        Student student = intent.getParcelableExtra("Student");

        // Use the student object as needed
        if (student != null) {
            // Display or use the student object
            enrollmentStudentBinding.welcomeEnroll.setText("Welcome "+student.getStdName());
        }

        enrollmentStudentBinding.searchButtonCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCourse();
            }
        });
    }

    private void searchCourse(){
        String courseCode = enrollmentStudentBinding.searchCourseText.getText().toString();

        courseViewModel.getCourseWithTeacher(courseCode).observe(this, courseWithTeacher -> {
            if (courseWithTeacher != null) {

                enrollmentStudentBinding.textCourseFoundStatus.setVisibility(View.GONE);
                enrollmentStudentBinding.layoutCourseDetails.setVisibility(View.VISIBLE);

                enrollmentStudentBinding.searchedCourseName.
                        setText(courseWithTeacher.course.getCourseName());
                enrollmentStudentBinding.searchedCourseTeacher.
                        setText(courseWithTeacher.teacher.getTeacherName());
                enrollmentStudentBinding.searchedCourseCredits.
                        setText(String.valueOf(courseWithTeacher.course.getCreditHours()));

            } else {
                enrollmentStudentBinding.layoutCourseDetails.setVisibility(View.GONE);
                enrollmentStudentBinding.textCourseFoundStatus.setVisibility(View.VISIBLE);
            }
        });
    }
}