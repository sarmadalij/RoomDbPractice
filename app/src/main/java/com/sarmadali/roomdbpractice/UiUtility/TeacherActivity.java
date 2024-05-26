package com.sarmadali.roomdbpractice.UiUtility;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.Entity.Teacher;
import com.sarmadali.roomdbpractice.ViewModel.TeacherViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityTeacherBinding;

public class TeacherActivity extends AppCompatActivity {

    private TeacherViewModel teacherViewModel;

    ActivityTeacherBinding teacherBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teacherBinding = ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(teacherBinding.getRoot());

        teacherViewModel = new ViewModelProvider(this).get(TeacherViewModel.class);

        teacherBinding.teacherSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTeacherData();
            }
        });

    }

    private void sendTeacherData(){

        Teacher teacherData = new Teacher(
                teacherBinding.teacherID.getText().toString(),
                teacherBinding.teacherName.getText().toString(),
                teacherBinding.teacherDept.getText().toString());

        teacherViewModel.insertTeacher(teacherData);

        Toast.makeText(TeacherActivity.this, "Send Teacher Data", Toast.LENGTH_SHORT).show();
    }
}