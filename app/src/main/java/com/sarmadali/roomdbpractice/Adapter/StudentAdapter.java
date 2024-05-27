package com.sarmadali.roomdbpractice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.R;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    private List<Student> studentList = new ArrayList<>();

    //constructor
    public StudentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_layout, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        Student studentModel = studentList.get(position);

        //set data in views
        holder.stdID.setText(studentModel.getStdId());
        holder.stdName.setText(studentModel.getStdName());
        holder.stdDept.setText(studentModel.getStdDepartment());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setStudents(List<Student> students) {
        this.studentList = students;
        notifyDataSetChanged();
    }

    //viewHolder class
    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView stdID, stdName, stdDept;
        ImageView deleteStudent;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            stdID = itemView.findViewById(R.id.fieldId);
            stdName = itemView.findViewById(R.id.fieldName);
            stdDept = itemView.findViewById(R.id.fieldDept);

            deleteStudent = itemView.findViewById(R.id.deleteImage);
        }
    }
}
