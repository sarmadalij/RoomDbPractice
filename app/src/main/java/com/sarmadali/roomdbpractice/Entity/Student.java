package com.sarmadali.roomdbpractice.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student implements Parcelable {

    public Student() {
    }

    @NonNull
    @PrimaryKey()
    private String stdId;
    private String stdName;
    private String stdDepartment;
    private String stdPassword;

    public Student(String stdId, @NonNull String stdName, String stdDepartment) {
        this.stdName = stdName;
        this.stdId = stdId;
        this.stdDepartment = stdDepartment;

    }
    public Student(String stdId, @NonNull String stdName, String stdDepartment, String stdPassword) {
        this.stdName = stdName;
        this.stdId = stdId;
        this.stdDepartment = stdDepartment;
        this.stdPassword = stdPassword;
    }

    //parcelable constructor
    protected Student(Parcel in) {
        stdId = in.readString();
        stdName = in.readString();
        stdDepartment = in.readString();
        stdPassword = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    //setters
    public void setStdName(String stdName) {
        this.stdName = stdName;
    }
    public void setStdId(@NonNull String stdId) {
        this.stdId = stdId;
    }
    public void setStdDepartment(String stdDepartment) {
        this.stdDepartment = stdDepartment;
    }

    public void setStdPassword(String stdPassword) {
        this.stdPassword = stdPassword;
    }

    //getters
    public String getStdName() {
        return stdName;
    }
    @NonNull
    public String getStdId() {
        return stdId;
    }
    public String getStdDepartment() {
        return stdDepartment;
    }

    public String getStdPassword() {
        return stdPassword;
    }

    //parcelable
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(stdId);
        dest.writeString(stdName);
        dest.writeString(stdDepartment);
        dest.writeString(stdPassword);
    }
}
