package com.example.a1210520_yara_khattab;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StudentCourseDB.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_STUDENTS = "Students";
    public static final String STUDENT_ID = "student_id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_EMAIL = "email";

    public static final String TABLE_COURSES = "Courses";
    public static final String COURSE_ID = "course_id";
    public static final String COURSE_TITLE = "title";
    public static final String COURSE_INSTRUCTOR = "instructor";

    public static final String TABLE_ENROLLMENTS = "Enrollments";
    public static final String ENROLLMENT_ID = "enrollment_id";
    public static final String ENROLLMENT_STUDENT_ID = "student_id";
    public static final String ENROLLMENT_COURSE_ID = "course_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + " (" +
                STUDENT_ID + " INTEGER PRIMARY KEY, " +
                STUDENT_NAME + " TEXT NOT NULL, " +
                STUDENT_EMAIL + " TEXT UNIQUE NOT NULL);";

        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + " (" +
                COURSE_ID + " INTEGER PRIMARY KEY, " +
                COURSE_TITLE + " TEXT NOT NULL, " +
                COURSE_INSTRUCTOR + " TEXT NOT NULL);";

        String CREATE_ENROLLMENTS_TABLE = "CREATE TABLE " + TABLE_ENROLLMENTS + " (" +
                ENROLLMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ENROLLMENT_STUDENT_ID + " INTEGER, " +
                ENROLLMENT_COURSE_ID + " INTEGER, " +
                "FOREIGN KEY(" + ENROLLMENT_STUDENT_ID + ") REFERENCES " + TABLE_STUDENTS + "(" + STUDENT_ID + "), " +
                "FOREIGN KEY(" + ENROLLMENT_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSE_ID + "));";

        db.execSQL(CREATE_STUDENTS_TABLE);
        db.execSQL(CREATE_COURSES_TABLE);
        db.execSQL(CREATE_ENROLLMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENROLLMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public boolean insertStudent(int id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_ID, id);
        values.put(STUDENT_NAME, name);
        values.put(STUDENT_EMAIL, email);
        long result = db.insert(TABLE_STUDENTS, null, values);
        return result != -1;
    }

    public boolean insertCourse(int id, String title, String instructor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_ID, id);
        values.put(COURSE_TITLE, title);
        values.put(COURSE_INSTRUCTOR, instructor);
        long result = db.insert(TABLE_COURSES, null, values);
        return result != -1;
    }

    public boolean enrollStudent(int studentId, int courseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ENROLLMENT_STUDENT_ID, studentId);
        values.put(ENROLLMENT_COURSE_ID, courseId);
        long result = db.insert(TABLE_ENROLLMENTS, null, values);
        return result != -1;
    }


    public List<String> getAllStudents() {
        List<String> results = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT s." + STUDENT_NAME + ", c." + COURSE_TITLE +
                " FROM " + TABLE_STUDENTS + " s " +
                "JOIN " + TABLE_ENROLLMENTS + " e ON s." + STUDENT_ID + " = e." + ENROLLMENT_STUDENT_ID +
                " JOIN " + TABLE_COURSES + " c ON c." + COURSE_ID + " = e." + ENROLLMENT_COURSE_ID;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            results.add("Student: " + cursor.getString(0) + " - Course: " + cursor.getString(1));
        }
        cursor.close();
        return results;
    }


    public List<String> getStudentsWithMoreThanTwoCourses() {
        List<String> results = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT s." + STUDENT_NAME + ", COUNT(e." + ENROLLMENT_COURSE_ID + ") AS course_count " +
                "FROM " + TABLE_STUDENTS + " s " +
                "JOIN " + TABLE_ENROLLMENTS + " e ON s." + STUDENT_ID + " = e." + ENROLLMENT_STUDENT_ID +
                " GROUP BY s." + STUDENT_ID +
                " HAVING course_count > 2";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            results.add("Student: " + cursor.getString(0) + " - Courses Enrolled: " + cursor.getInt(1));
        }
        cursor.close();
        return results;
    }


    public List<String> getCoursesWithNoStudents() {
        List<String> results = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT c." + COURSE_TITLE +
                " FROM " + TABLE_COURSES + " c " +
                "LEFT JOIN " + TABLE_ENROLLMENTS + " e ON c." + COURSE_ID + " = e." + ENROLLMENT_COURSE_ID +
                " WHERE e." + ENROLLMENT_ID + " IS NULL";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            results.add("Course: " + cursor.getString(0));
        }
        cursor.close();
        return results;
    }


    public List<String> getMostPopularCourse() {
        List<String> results = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT c." + COURSE_TITLE + ", COUNT(e." + ENROLLMENT_ID + ") AS count " +
                "FROM " + TABLE_COURSES + " c " +
                "JOIN " + TABLE_ENROLLMENTS + " e ON c." + COURSE_ID + " = e." + ENROLLMENT_COURSE_ID +
                " GROUP BY c." + COURSE_ID +
                " ORDER BY count DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            results.add("Most Popular Course: " + cursor.getString(0) + " (" + cursor.getInt(1) + " students)");
        }
        cursor.close();
        return results;
    }


    public List<String> getStudentSchedule(int studentId) {
        List<String> results = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT c." + COURSE_TITLE + ", c." + COURSE_INSTRUCTOR +
                " FROM " + TABLE_ENROLLMENTS + " e " +
                "JOIN " + TABLE_COURSES + " c ON e." + ENROLLMENT_COURSE_ID + " = c." + COURSE_ID +
                " WHERE e." + ENROLLMENT_STUDENT_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(studentId)});
        while (cursor.moveToNext()) {
            results.add("Course: " + cursor.getString(0) + " - Instructor: " + cursor.getString(1));
        }
        cursor.close();
        return results;
    }
}
