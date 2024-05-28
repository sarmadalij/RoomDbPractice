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
import com.sarmadali.roomdbpractice.Adapter.TeacherAdapter;
import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.Entity.Teacher;
import com.sarmadali.roomdbpractice.ViewModel.TeacherViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityTeacherBinding;

import java.util.List;

public class TeacherActivity extends AppCompatActivity implements TeacherAdapter.OnDeleteTeacherClickListener{

    private TeacherViewModel teacherViewModel;

    ActivityTeacherBinding teacherBinding;
    RecyclerView teacherRecyclerView;
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

        showTeacherData();
    }

    private void sendTeacherData(){

        Teacher teacherData = new Teacher(
                teacherBinding.teacherID.getText().toString(),
                teacherBinding.teacherName.getText().toString(),
                teacherBinding.teacherDept.getText().toString());

        teacherViewModel.insertTeacher(teacherData);

        Toast.makeText(TeacherActivity.this, "Send Teacher Data", Toast.LENGTH_SHORT).show();
    }

    private void showTeacherData(){
        teacherRecyclerView = teacherBinding.teacherRecyclerLayout;
        teacherRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create the adapter
        final TeacherAdapter adapter = new TeacherAdapter(this);
        adapter.setOnDeleteClickListener(this);
        teacherRecyclerView.setAdapter(adapter);

        // Observe the LiveData
        teacherViewModel.getTeacherAllData().observe(this, new Observer<List<Teacher>>() {
            @Override
            public void onChanged(@Nullable List<Teacher> teachers) {
                // Update the adapter's data
                adapter.setTeacher(teachers);
            }
        });
    }

    @Override
    public void onDeleteClick(Teacher teacher) {
        teacherViewModel.deleteTeacher(teacher);
        Toast.makeText(this, teacher.getTeacherId()+"'s Data Deleted", Toast.LENGTH_SHORT).show();
    }
}