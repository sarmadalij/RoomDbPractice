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
import com.sarmadali.roomdbpractice.Entity.Enrollment;
import com.sarmadali.roomdbpractice.Entity.EnrollmentWithDetails;
import com.sarmadali.roomdbpractice.R;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentAdapter extends RecyclerView.Adapter<EnrollmentAdapter.EnrollmentViewHolder>{

    Context context;
    private List<EnrollmentWithDetails> enrollments = new ArrayList<>();

    public EnrollmentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EnrollmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_layout, parent, false);
        return new EnrollmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrollmentViewHolder holder, int position) {
        EnrollmentWithDetails currentEnrolled = enrollments.get(position);

        holder.enrollmentID.setText(String.valueOf(currentEnrolled.enrollment.getEnrollId()));
        holder.enrollmentCourseName.setText(currentEnrolled.course.getCourseName());
        holder.enrollmentCourseCredit.setText(String.valueOf(currentEnrolled.course.getCreditHours()));
        holder.enrollmentCourseTeacher.setText(currentEnrolled.teacher.getTeacherName());

        holder.updateEnrollment.setVisibility(View.GONE);

        holder.deleteEnrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null){
                    onDeleteClickListener.onDeleteClick(currentEnrolled.enrollment);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return enrollments.size();
    }
    public void setEnrollments(List<EnrollmentWithDetails> enrollments) {
        this.enrollments = enrollments;
        notifyDataSetChanged();
    }

    //view holder class
    class EnrollmentViewHolder extends RecyclerView.ViewHolder {

        TextView enrollmentID, enrollmentCourseName, enrollmentCourseCredit, enrollmentCourseTeacher;
        ImageView deleteEnrollment, updateEnrollment;
        public EnrollmentViewHolder(@NonNull View itemView) {
            super(itemView);
            enrollmentID = itemView.findViewById(R.id.fieldId);
            enrollmentCourseName = itemView.findViewById(R.id.fieldName);
            enrollmentCourseCredit = itemView.findViewById(R.id.fieldDept);
            enrollmentCourseTeacher = itemView.findViewById(R.id.fieldCourseTeacher);

            deleteEnrollment = itemView.findViewById(R.id.deleteImage);
            updateEnrollment = itemView.findViewById(R.id.updateImage);
        }
    }

    // Interface for item delete event
    public interface OnDeleteEnrollmentClickListener {
        void onDeleteClick(Enrollment enrollment);
    }
    private EnrollmentAdapter.OnDeleteEnrollmentClickListener onDeleteClickListener;
    public void setOnDeleteClickListener(EnrollmentAdapter.OnDeleteEnrollmentClickListener listener) {
        this.onDeleteClickListener = listener;
    }
}
