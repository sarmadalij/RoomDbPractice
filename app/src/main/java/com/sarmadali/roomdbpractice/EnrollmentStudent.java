package com.sarmadali.roomdbpractice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sarmadali.roomdbpractice.Adapter.EnrollmentAdapter;
import com.sarmadali.roomdbpractice.Entity.Enrollment;
import com.sarmadali.roomdbpractice.Entity.EnrollmentWithDetails;
import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.ViewModel.CourseViewModel;
import com.sarmadali.roomdbpractice.ViewModel.EnrollmentViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityEnrollmentStudentBinding;

public class EnrollmentStudent extends AppCompatActivity implements
     EnrollmentAdapter.OnDeleteEnrollmentClickListener {

    ActivityEnrollmentStudentBinding enrollmentStudentBinding;
    private CourseViewModel courseViewModel;
    private EnrollmentViewModel enrollmentViewModel;
    private Student loggedInStudent;
    private RecyclerView enrollmentRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enrollmentStudentBinding = ActivityEnrollmentStudentBinding.inflate(getLayoutInflater());
        setContentView(enrollmentStudentBinding.getRoot());

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        enrollmentViewModel = new ViewModelProvider(this).get(EnrollmentViewModel.class);

        // Retrieve the Student object from the Intent
        Intent intent = getIntent();
        Student student = intent.getParcelableExtra("Student");

        // Use the student object as needed
        if (student != null) {
            // Display or use the student object
            enrollmentStudentBinding.welcomeEnroll.setText("Welcome "+student.getStdName());
            loggedInStudent = student;
        }

        enrollmentStudentBinding.searchButtonCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCourse();
            }
        });

        enrollmentStudentBinding.enrollThisCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrollCourse();
            }
        });

        loadEnrolledCourses();
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

    private void enrollCourse(){
        String courseCode = enrollmentStudentBinding.searchCourseText.getText().toString();
        if (TextUtils.isEmpty(courseCode)) {
            enrollmentStudentBinding.searchCourseText.setError("This Field can't be empty");
            return; // Stop execution if id is not given/empty
        }

        courseViewModel.getCourseWithTeacher(courseCode).observe(this,courseWithTeachers -> {
            if (courseWithTeachers != null){
                Enrollment enrollment = new Enrollment(
                        loggedInStudent.getStdId(),
                        courseWithTeachers.course.getCourseCode(),
                        courseWithTeachers.teacher.getTeacherId()
                        );
                enrollmentViewModel.insertEnrollment(enrollment);

                loadEnrolledCourses();
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadEnrolledCourses() {
        enrollmentRecyclerview = enrollmentStudentBinding.enrollmentRecyclerLayout;
        enrollmentRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        final EnrollmentAdapter adapter = new EnrollmentAdapter(this);
        adapter.setOnDeleteClickListener(this);
        enrollmentRecyclerview.setAdapter(adapter);

        enrollmentViewModel.getEnrollmentsForStudent(loggedInStudent.getStdId()).observe(this, enrollments -> {
            if (enrollments != null && !enrollments.isEmpty()) {
                adapter.setEnrollments(enrollments);
            } else {
                Toast.makeText(this, "No enrolled courses found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDeleteClick(Enrollment enrollment) {
        enrollmentViewModel.deleteEnrollment(enrollment);
    }
}