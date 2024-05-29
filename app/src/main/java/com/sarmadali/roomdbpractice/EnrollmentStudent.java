package com.sarmadali.roomdbpractice;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.databinding.ActivityEnrollmentStudentBinding;

public class EnrollmentStudent extends AppCompatActivity {

    ActivityEnrollmentStudentBinding enrollmentStudentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enrollmentStudentBinding = ActivityEnrollmentStudentBinding.inflate(getLayoutInflater());
        setContentView(enrollmentStudentBinding.getRoot());

        // Retrieve the Student object from the Intent
        Intent intent = getIntent();
        Student student = intent.getParcelableExtra("Student");

        // Use the student object as needed
        if (student != null) {
            // Display or use the student object
            enrollmentStudentBinding.welcomeEnroll.setText("Welcome "+student.getStdName());
        }
    }
}