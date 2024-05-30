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

import com.sarmadali.roomdbpractice.Adapter.CourseAdapter;
import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.CourseWithTeacher;
import com.sarmadali.roomdbpractice.R;
import com.sarmadali.roomdbpractice.ViewModel.CourseViewModel;
import com.sarmadali.roomdbpractice.databinding.ActivityCourseBinding;

import java.util.List;

public class CourseActivity extends AppCompatActivity
        implements
        CourseAdapter.OnDeleteCourseClickListener,
        CourseAdapter.OnUpdateCourseClickListener {

    private CourseViewModel courseViewModel;
    ActivityCourseBinding courseBinding;
    RecyclerView courseRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseBinding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(courseBinding.getRoot());

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        courseBinding.courseSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCourseData();
            }
        });

        showCourseData();
    }

    private void sendCourseData(){

        Course courseData = new Course(
                courseBinding.courseCode.getText().toString(),
                courseBinding.courseName.getText().toString(),
                Integer.parseInt(courseBinding.courseCredit.getText().toString()),
                courseBinding.courseTeacher.getText().toString());

        courseViewModel.insertCourse(courseData);

        Toast.makeText(CourseActivity.this, "Send Course Data", Toast.LENGTH_SHORT).show();
    }

    private void showCourseData(){
        courseRecyclerView = courseBinding.courseRecyclerLayout;
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create the adapter
        final CourseAdapter adapter = new CourseAdapter(this);
        adapter.setOnDeleteClickListener(this);
        adapter.setOnUpdateClickListener(this);
        courseRecyclerView.setAdapter(adapter);

        // Observe the LiveData
//        courseViewModel.getCourseAllData().observe(this, new Observer<List<Course>>() {
//            @Override
//            public void onChanged(@Nullable List<Course> courses) {
//                // Update the adapter's data
//                adapter.setCourse(courses);
//            }
//        });
        courseViewModel.getCoursesWithTeachers().observe(this, new Observer<List<CourseWithTeacher>>() {
            @Override
            public void onChanged(List<CourseWithTeacher> courseWithTeachers) {
                adapter.setCourses(courseWithTeachers);
            }
        });
    }

    @Override
    public void onDeleteClick(Course course) {
        // Handle item deletion here
        courseViewModel.deleteCourse(course);
        Toast.makeText(this, course.getCourseName() +" Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateClick(Course course) {
        // Inflate the popup layout
        View popupView = LayoutInflater.from(this).inflate(R.layout.update_layout, null);

        //Populate EditText fields with data of the corresponding item
        EditText editTextCourseCode = popupView.findViewById(R.id.editTextCourseCode);
        EditText editTextCourseName = popupView.findViewById(R.id.editTextCourseName);
        EditText editTextCourseCredit = popupView.findViewById(R.id.editTextCredit);
        EditText editTextCourseTeacher = popupView.findViewById(R.id.editCourseTeacher);

        editTextCourseTeacher.setVisibility(View.VISIBLE);

        //setting text in the EditText
        editTextCourseCode.setText(course.getCourseCode());
        editTextCourseName.setText(course.getCourseName());
        editTextCourseCredit.setText(String.valueOf(course.getCreditHours()));
        editTextCourseTeacher.setText(course.getTeacherId());

        // Create and show the popup dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                .setView(popupView)
                .setTitle("Update Course")
                .setPositiveButton("Update", null) // We'll override this later
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // Override the positive button click to handle update action
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            // Get updated data from EditText fields
            String updatedCourseCode = editTextCourseCode.getText().toString();
            String updatedCourseName = editTextCourseName.getText().toString();
            int updatedCourseCredit = Integer.parseInt(editTextCourseCredit.getText().toString());
            String updatedCourseTeacher = editTextCourseTeacher.getText().toString();

            // Update the course in the database
            Course updatedCourse = new Course(updatedCourseCode, updatedCourseName, updatedCourseCredit,updatedCourseTeacher);
            courseViewModel.updateCourse(course, updatedCourse);

            // Dismiss the dialog
            alertDialog.dismiss();
        });

    }
}