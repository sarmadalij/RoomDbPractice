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
import com.sarmadali.roomdbpractice.Entity.Teacher;
import com.sarmadali.roomdbpractice.R;

import java.util.ArrayList;
import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    Context context;
    private List<Teacher> teacherList = new ArrayList<>();

    //constructor
    public TeacherAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_layout, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {

        Teacher teacherModel = teacherList.get(position);

        //set data in views
        holder.teacherID.setText(teacherModel.getTeacherId());
        holder.teacherName.setText(teacherModel.getTeacherName());
        holder.teacherDept.setText(teacherModel.getTeacherDept());

    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacherList = teacher;
        notifyDataSetChanged();
    }

    //viewHolder class
    public static class TeacherViewHolder extends RecyclerView.ViewHolder{

        TextView teacherID, teacherName, teacherDept;
        ImageView deleteTeacher;
        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherID = itemView.findViewById(R.id.fieldId);
            teacherName = itemView.findViewById(R.id.fieldName);
            teacherDept = itemView.findViewById(R.id.fieldDept);

            deleteTeacher = itemView.findViewById(R.id.deleteImage);

        }
    }
}
