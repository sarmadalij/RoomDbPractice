package com.sarmadali.roomdbpractice.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.sarmadali.roomdbpractice.Entity.Enrollment;
import com.sarmadali.roomdbpractice.Entity.EnrollmentWithDetails;
import com.sarmadali.roomdbpractice.Repository.EnrollmentRepository;

import java.util.List;

public class EnrollmentViewModel extends AndroidViewModel {
    private EnrollmentRepository enrollmentRepository;
    public EnrollmentViewModel(@NonNull Application application) {
        super(application);
        enrollmentRepository = new EnrollmentRepository(application);
    }
    public void insertEnrollment(Enrollment enrollment) {
        enrollmentRepository.insertEnrollment(enrollment);
    }
    public LiveData<List<EnrollmentWithDetails>> getEnrollmentsForStudent(String studentId) {
        return enrollmentRepository.getEnrollmentsForStudent(studentId);
    }
    public void deleteEnrollment(Enrollment enrollment){
        enrollmentRepository.deleteEnrollment(enrollment);
    }
}
