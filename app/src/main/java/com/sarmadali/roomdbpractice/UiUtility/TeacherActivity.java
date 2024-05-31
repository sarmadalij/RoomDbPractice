package com.sarmadali.roomdbpractice.UiUtility;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sarmadali.roomdbpractice.Adapter.TeacherAdapter;
import com.sarmadali.roomdbpractice.Entity.Teacher;
import com.sarmadali.roomdbpractice.R;
import com.sarmadali.roomdbpractice.ViewModel.TeacherViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityTeacherBinding;

import java.util.List;

public class TeacherActivity extends AppCompatActivity implements
        TeacherAdapter.OnDeleteTeacherClickListener,
        TeacherAdapter.OnUpdateTeacherClickListener {
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
        adapter.setOnUpdateClickListener(this);

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
    @Override
    public void onUpdateClick(Teacher teacher) {
        // Inflate the popup layout
        View popupView = LayoutInflater.from(this).inflate(R.layout.update_layout, null);

        //Populate EditText fields with data of the corresponding item
        EditText editTextCourseCode = popupView.findViewById(R.id.editTextCourseCode);
        EditText editTextCourseName = popupView.findViewById(R.id.editTextCourseName);
        EditText editTextCourseCredit = popupView.findViewById(R.id.editTextCredit);

        //setting text in the EditText
        editTextCourseCode.setText(teacher.getTeacherId());
        editTextCourseName.setText(teacher.getTeacherName());
        editTextCourseCredit.setText(teacher.getTeacherDept());

        // Create and show the popup dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                .setView(popupView)
                .setTitle("Update Teacher")
                .setPositiveButton("Update", null) // We'll override this later
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // Override the positive button click to handle update action
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            // Get updated data from EditText fields
            String updatedTeacherID = editTextCourseCode.getText().toString();
            String updatedTeacherName = editTextCourseName.getText().toString();
            String updatedTeacherDepart = editTextCourseCredit.getText().toString();

            // Update the teacher in the database
            Teacher updatedTeacher = new Teacher(updatedTeacherID, updatedTeacherName, updatedTeacherDepart);
            teacherViewModel.updateTeacher(teacher, updatedTeacher);

            // Dismiss the dialog
            alertDialog.dismiss();
        });
    }
}