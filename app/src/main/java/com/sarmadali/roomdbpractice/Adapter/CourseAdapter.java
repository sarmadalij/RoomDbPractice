package com.sarmadali.roomdbpractice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.CourseWithTeacher;
import com.sarmadali.roomdbpractice.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    private List<Course> courseList = new ArrayList<>();
    private List<CourseWithTeacher> courses = new ArrayList<>();
    //constructor
    public CourseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_layout, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

//        Course courseModel = courseList.get(position);
        //changed
        CourseWithTeacher current = courses.get(position);

        //set data in views
//        holder.courseCode.setText(courseModel.getCourseCode());
//        holder.courseName.setText(courseModel.getCourseName());
//        holder.courseCredit.setText(String.valueOf(courseModel.getCreditHours()));
        //changed
        holder.courseCode.setText(current.course.getCourseCode());
        holder.courseName.setText(current.course.getCourseName());
        holder.courseCredit.setText(String.valueOf(current.course.getCreditHours()));

        if (current.teacher != null){
        holder.courseTeacher.setText(current.teacher.getTeacherName());
        } else {
            holder.courseTeacher.setText("Not Assigned");
        }

        //delete
        holder.deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(current.course);
                }
            }
        });

        //update
        holder.updateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUpdateClickListener != null) {
                    onUpdateClickListener.onUpdateClick(current.course);
                }
            }
        });
    }

    //changed
    @Override
    public int getItemCount() {
        return courses.size();
    }

//    public void setCourse(List<Course> course) {
//        this.courseList = course;
//        notifyDataSetChanged();
//    }
    //changed
    public void setCourses(List<CourseWithTeacher> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    //viewHolder class
    public static class CourseViewHolder extends RecyclerView.ViewHolder{

        TextView courseCode, courseName, courseCredit, courseTeacher;
        ImageView deleteCourse, updateCourse;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseCode = itemView.findViewById(R.id.fieldId);
            courseName = itemView.findViewById(R.id.fieldName);
            courseCredit = itemView.findViewById(R.id.fieldDept);
            courseTeacher = itemView.findViewById(R.id.fieldCourseTeacher);

            deleteCourse = itemView.findViewById(R.id.deleteImage);
            updateCourse = itemView.findViewById(R.id.updateImage);

        }
    }

    // Interface for item delete event
    public interface OnDeleteCourseClickListener {
        void onDeleteClick(Course course);
    }
    private OnDeleteCourseClickListener onDeleteClickListener;
    public void setOnDeleteClickListener(OnDeleteCourseClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    // Interface for item update events
    public interface OnUpdateCourseClickListener {
        void onUpdateClick(Course course);
    }
    private OnUpdateCourseClickListener onUpdateClickListener;
    public void setOnUpdateClickListener(OnUpdateCourseClickListener listener) {
        this.onUpdateClickListener = listener;
    }

}
