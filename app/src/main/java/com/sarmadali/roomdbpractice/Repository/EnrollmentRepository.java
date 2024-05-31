package com.sarmadali.roomdbpractice.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.sarmadali.roomdbpractice.DAO.EnrollmentDao;
import com.sarmadali.roomdbpractice.Database.DatabaseApp;
import com.sarmadali.roomdbpractice.Entity.Enrollment;
import com.sarmadali.roomdbpractice.Entity.EnrollmentWithDetails;

import java.util.List;

public class EnrollmentRepository {
    private EnrollmentDao enrollmentDao;

    public EnrollmentRepository(Application application){
        DatabaseApp db = DatabaseApp.getDatabase(application);
        enrollmentDao = db.enrollmentDao();
    }
    public void insertEnrollment(Enrollment enrollment) {
        DatabaseApp.databaseWriteExecutor.execute(() -> {
            enrollmentDao.insertEnrollment(enrollment);
        });
    }
    public LiveData<List<EnrollmentWithDetails>> getEnrollmentsForStudent(String studentId) {
        return enrollmentDao.getEnrollmentsForStudent(studentId);
    }
    public void deleteEnrollment(Enrollment enrollment){
        DatabaseApp.databaseWriteExecutor.execute(()->{
            enrollmentDao.deleteEnrollment(enrollment);
        });
    }
}
