package com.sarmadali.roomdbpractice.UiUtility;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sarmadali.roomdbpractice.databinding.ActivityStudentBinding;

public class StudentActivity extends AppCompatActivity {

    ActivityStudentBinding studentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentBinding = ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(studentBinding.getRoot());

        studentBinding.stdSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentActivity.this, "Send Student Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}