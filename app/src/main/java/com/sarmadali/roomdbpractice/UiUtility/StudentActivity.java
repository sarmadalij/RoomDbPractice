package com.sarmadali.roomdbpractice.UiUtility;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.ViewModel.StudentViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityStudentBinding;

public class StudentActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    ActivityStudentBinding studentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentBinding = ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(studentBinding.getRoot());

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        studentBinding.stdSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendStudentData();
            }
        });

    }

    private void sendStudentData(){

        Student studentData = new Student(
                studentBinding.stdID.getText().toString(),
                studentBinding.stdName.getText().toString(),
                studentBinding.stdDept.getText().toString());
        studentViewModel.insertStudent(studentData);

        Toast.makeText(StudentActivity.this, "Send Student Data", Toast.LENGTH_SHORT).show();
    }

}

