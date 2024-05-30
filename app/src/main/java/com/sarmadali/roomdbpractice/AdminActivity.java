package com.sarmadali.roomdbpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sarmadali.roomdbpractice.UiUtility.CourseActivity;
import com.sarmadali.roomdbpractice.UiUtility.StudentActivity;
import com.sarmadali.roomdbpractice.UiUtility.TeacherActivity;
import com.sarmadali.roomdbpractice.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding adminBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(adminBinding.getRoot());

        adminBinding.studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminActivity.this, "Student Data", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        });

        adminBinding.teacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminActivity.this, "Teacher Data", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this, TeacherActivity.class);
                startActivity(intent);
            }
        });

        adminBinding.courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminActivity.this, "Course Data", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });
    }
}