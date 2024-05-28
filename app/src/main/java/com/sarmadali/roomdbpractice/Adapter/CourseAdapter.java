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
import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    private List<Course> courseList = new ArrayList<>();

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

        Course courseModel = courseList.get(position);

        //set data in views
        holder.courseCode.setText(courseModel.getCourseCode());
        holder.courseName.setText(courseModel.getCourseName());
        holder.courseCredit.setText(String.valueOf(courseModel.getCreditHours()));

        holder.deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(courseModel);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setCourse(List<Course> course) {
        this.courseList = course;
        notifyDataSetChanged();
    }

    //viewHolder class
    public static class CourseViewHolder extends RecyclerView.ViewHolder{

        TextView courseCode, courseName, courseCredit;
        ImageView deleteCourse;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseCode = itemView.findViewById(R.id.fieldId);
            courseName = itemView.findViewById(R.id.fieldName);
            courseCredit = itemView.findViewById(R.id.fieldDept);

            deleteCourse = itemView.findViewById(R.id.deleteImage);

        }
    }


    //to delete

    // Interface for item click events
    public interface OnDeleteCourseClickListener {
        void onDeleteClick(Course course);
    }

    private OnDeleteCourseClickListener onDeleteClickListener;

    public void setOnDeleteClickListener(OnDeleteCourseClickListener listener) {
        this.onDeleteClickListener = listener;
    }
}
