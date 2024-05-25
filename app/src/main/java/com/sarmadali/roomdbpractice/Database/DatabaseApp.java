package com.sarmadali.roomdbpractice.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sarmadali.roomdbpractice.DAO.CourseDao;
import com.sarmadali.roomdbpractice.DAO.StudentDao;
import com.sarmadali.roomdbpractice.DAO.TeacherDao;
import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.Entity.Teacher;

@Database(entities = {Student.class, Course.class, Teacher.class}, version = 1, exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract CourseDao courseDao();
    public abstract TeacherDao teacherDao();

    private static volatile DatabaseApp INSTANCE;

    static DatabaseApp getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (DatabaseApp.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseApp.class, "database_app")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
