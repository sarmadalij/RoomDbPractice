package com.sarmadali.roomdbpractice.UiUtility;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.Teacher;
import com.sarmadali.roomdbpractice.ViewModel.CourseViewModel;
import com.sarmadali.roomdbpractice.ViewModel.TeacherViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityCourseBinding;

public class CourseActivity extends AppCompatActivity {

    private CourseViewModel courseViewModel;
    ActivityCourseBinding courseBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseBinding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(courseBinding.getRoot());

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        courseBinding.courseSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCourseData();
            }
        });

    }

    private void sendCourseData(){

        Course courseData = new Course(
                courseBinding.courseCode.getText().toString(),
                courseBinding.courseName.getText().toString(),
                Integer.parseInt(courseBinding.courseCredit.getText().toString()));

        courseViewModel.insertCourse(courseData);

        Toast.makeText(CourseActivity.this, "Send Course Data", Toast.LENGTH_SHORT).show();
    }
}