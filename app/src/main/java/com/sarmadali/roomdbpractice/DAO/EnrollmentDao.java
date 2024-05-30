package com.sarmadali.roomdbpractice.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sarmadali.roomdbpractice.Entity.Enrollment;
import com.sarmadali.roomdbpractice.Entity.EnrollmentWithDetails;

import java.util.List;

@Dao
public interface EnrollmentDao {
    @Insert
    void insertEnrollment(Enrollment enrollment);

    @Query("SELECT * FROM enrollment WHERE studentId = :studentId")
    LiveData<List<EnrollmentWithDetails>> getEnrollmentsForStudent(String studentId);

    @Delete
    void deleteEnrollment(Enrollment enrollment);
}
