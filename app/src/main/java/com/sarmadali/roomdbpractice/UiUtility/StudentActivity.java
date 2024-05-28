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

import com.sarmadali.roomdbpractice.Adapter.StudentAdapter;
import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.R;
import com.sarmadali.roomdbpractice.ViewModel.StudentViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityStudentBinding;

import java.util.List;

public class StudentActivity extends AppCompatActivity implements
        StudentAdapter.OnDeleteStudentClickListener,
        StudentAdapter.OnUpdateStudentClickListener{

    private StudentViewModel studentViewModel;
    ActivityStudentBinding studentBinding;
    RecyclerView studentRecyclerView;

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


        showStudentData();
    }

    private void sendStudentData(){

        Student studentData = new Student(
                studentBinding.stdID.getText().toString(),
                studentBinding.stdName.getText().toString(),
                studentBinding.stdDept.getText().toString());
        studentViewModel.insertStudent(studentData);

        Toast.makeText(StudentActivity.this, "Send Student Data", Toast.LENGTH_SHORT).show();
    }

    private void showStudentData(){
        studentRecyclerView = studentBinding.studentRecyclerLayout;
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create the adapter
        final StudentAdapter adapter = new StudentAdapter(this);
        adapter.setOnDeleteClickListener(this);
        adapter.setOnUpdateClickListener(this);

        studentRecyclerView.setAdapter(adapter);
        // Observe the LiveData
        studentViewModel.getStudentAllData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                // Update the adapter's data
                adapter.setStudents(students);
            }
        });
    }

    @Override
    public void onDeleteClick(Student student) {
        studentViewModel.deleteStudent(student);
        Toast.makeText(this, student.getStdId() +"'s Data Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateClick(Student student) {

        // Inflate the popup layout
        View popupView = LayoutInflater.from(this).inflate(R.layout.update_layout, null);

        //Populate EditText fields with data of the corresponding item
        EditText editTextCourseCode = popupView.findViewById(R.id.editTextCourseCode);
        EditText editTextCourseName = popupView.findViewById(R.id.editTextCourseName);
        EditText editTextCourseCredit = popupView.findViewById(R.id.editTextCredit);

        //setting text in the EditText
        editTextCourseCode.setText(student.getStdId());
        editTextCourseName.setText(student.getStdName());
        editTextCourseCredit.setText(student.getStdDepartment());

        // Create and show the popup dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                .setView(popupView)
                .setTitle("Update Student")
                .setPositiveButton("Update", null) // We'll override this later
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // Override the positive button click to handle update action
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            // Get updated data from EditText fields
            String updatedStdID = editTextCourseCode.getText().toString();
            String updatedStdName = editTextCourseName.getText().toString();
            String updatedStdDepart = editTextCourseCredit.getText().toString();

            // Update the student in the database
            Student updatedStudent = new Student(updatedStdID, updatedStdName, updatedStdDepart);
            studentViewModel.updateStudent(student, updatedStudent);

            // Dismiss the dialog
            alertDialog.dismiss();
        });
    }
}

