package com.sarmadali.roomdbpractice.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sarmadali.roomdbpractice.DAO.CourseDao;
import com.sarmadali.roomdbpractice.DAO.StudentDao;
import com.sarmadali.roomdbpractice.DAO.TeacherDao;
import com.sarmadali.roomdbpractice.Entity.Course;
import com.sarmadali.roomdbpractice.Entity.Student;
import com.sarmadali.roomdbpractice.Entity.Teacher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class, Course.class, Teacher.class}, version = 1, exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {

    public abstract StudentDao studentDao();

    public abstract CourseDao courseDao();

    public abstract TeacherDao teacherDao();

    private static volatile DatabaseApp INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DatabaseApp getDatabase(final Context context){
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

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
//    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            // If you want to keep data through app restarts,
//            // comment out the following block
//
//            databaseWriteExecutor.execute(()->{
//                // Populate the database in the background.
//                // If you want to start with more data, just add them.
//
//                StudentDao stdDao = INSTANCE.studentDao();
//                stdDao.deleteAll();
//            });
//        }
//    };
}
