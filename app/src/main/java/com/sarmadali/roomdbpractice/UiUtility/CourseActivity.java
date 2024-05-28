package com.sarmadali.roomdbpractice.UiUtility;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sarmadali.roomdbpractice.Adapter.CourseAdapter;
import com.sarmadali.roomdbpractice.Adapter.StudentAdapter;
import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.Entity.Teacher;
import com.sarmadali.roomdbpractice.ViewModel.CourseViewModel;
import com.sarmadali.roomdbpractice.ViewModel.TeacherViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityCourseBinding;

import java.util.List;

public class CourseActivity extends AppCompatActivity implements CourseAdapter.OnDeleteCourseClickListener {

    private CourseViewModel courseViewModel;
    ActivityCourseBinding courseBinding;

    RecyclerView courseRecyclerView;
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

        showCourseData();
    }

    private void sendCourseData(){

        Course courseData = new Course(
                courseBinding.courseCode.getText().toString(),
                courseBinding.courseName.getText().toString(),
                Integer.parseInt(courseBinding.courseCredit.getText().toString()));

        courseViewModel.insertCourse(courseData);

        Toast.makeText(CourseActivity.this, "Send Course Data", Toast.LENGTH_SHORT).show();
    }

    private void showCourseData(){
        courseRecyclerView = courseBinding.courseRecyclerLayout;
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create the adapter
        final CourseAdapter adapter = new CourseAdapter(this);
        adapter.setOnDeleteClickListener(this);

        courseRecyclerView.setAdapter(adapter);

        // Observe the LiveData
        courseViewModel.getCourseAllData().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(@Nullable List<Course> courses) {
                // Update the adapter's data
                adapter.setCourse(courses);
            }
        });
    }

    @Override
    public void onDeleteClick(Course course) {
        // Handle item deletion here
        courseViewModel.deleteCourse(course);
        Toast.makeText(this, course.getCourseName() +" Deleted", Toast.LENGTH_SHORT).show();
    }
}