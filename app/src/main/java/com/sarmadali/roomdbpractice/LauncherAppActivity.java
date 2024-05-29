package com.sarmadali.roomdbpractice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.ViewModel.StudentViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityLauncherAppBinding;

public class LauncherAppActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    ActivityLauncherAppBinding launcherAppBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcherAppBinding = ActivityLauncherAppBinding.inflate(getLayoutInflater());
        setContentView(launcherAppBinding.getRoot());

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        //Admin button
        launcherAppBinding.BtnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherAppActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // login button
        launcherAppBinding.BtnStdLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginStudent();
            }
        });
    }

    private void loginStudent(){

        String stdID = launcherAppBinding.txtStdIDLogin.getText().toString();
        String stdPassword = launcherAppBinding.txtStdPassLogin.getText().toString();

        if (TextUtils.isEmpty(stdID)) {
            launcherAppBinding.txtStdIDLogin.setError("Cannot be empty");
            return; // Stop execution if id is not given/empty
        }

        if (TextUtils.isEmpty(stdPassword)) {
            launcherAppBinding.txtStdPassLogin.setError("Password cannot be empty");
            return; // Stop execution if password is empty
        }

        //login through observe live data
        studentViewModel.loginStudent(stdID, stdPassword).observe(this, userStudent -> {
            if (userStudent != null){
                Toast.makeText(LauncherAppActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LauncherAppActivity.this, EnrollmentStudent.class);
                intent.putExtra("Student", (Parcelable) userStudent);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}