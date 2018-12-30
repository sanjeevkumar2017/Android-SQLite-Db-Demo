package com.skumar.sqlitedbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableControllerStudent extends DatabaseHandler {

    public TableControllerStudent(Context context) {
        super(context);
    }

    public boolean create(Student student) {
        ContentValues values = new ContentValues();
        values.put(AppConstants.STUDENT_NAME, student.studentName);
        values.put(AppConstants.STUDENT_EMAIL, student.email);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessful = db.insert(AppConstants.TABLE_STUDENT, null, values) > 0;
        db.close();
        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + AppConstants.TABLE_STUDENT;
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();
        return recordCount;
    }

    public List<Student> read() {
        List<Student> recordsList = new ArrayList<Student>();
        String sql = "SELECT * FROM " + AppConstants.TABLE_STUDENT + " ORDER BY id DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(AppConstants.STUDENT_ID)));
                String studentFirstname = cursor.getString(cursor.getColumnIndex(AppConstants.STUDENT_NAME));
                String studentEmail = cursor.getString(cursor.getColumnIndex(AppConstants.STUDENT_EMAIL));
                Student objectStudent = new Student();
                objectStudent.id = id;
                objectStudent.studentName = studentFirstname;
                objectStudent.email = studentEmail;
                recordsList.add(objectStudent);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordsList;
    }

    public Student readSingleRecord(int studentId) {
        Student objectStudent = null;
        String sql = "SELECT * FROM " + AppConstants.TABLE_STUDENT + " WHERE id = " + studentId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(AppConstants.STUDENT_ID)));
            String firstname = cursor.getString(cursor.getColumnIndex(AppConstants.STUDENT_NAME));
            String email = cursor.getString(cursor.getColumnIndex(AppConstants.STUDENT_EMAIL));
            objectStudent = new Student();
            objectStudent.id = id;
            objectStudent.studentName = firstname;
            objectStudent.email = email;
        }
        cursor.close();
        db.close();
        return objectStudent;
    }

    public boolean update(Student objectStudent) {
        ContentValues values = new ContentValues();
        values.put(AppConstants.STUDENT_NAME, objectStudent.studentName);
        values.put(AppConstants.STUDENT_EMAIL, objectStudent.email);
        String where = "id = ?";
        String[] whereArgs = {Integer.toString(objectStudent.id)};
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update(AppConstants.TABLE_STUDENT, values, where, whereArgs) > 0;
        db.close();
        return updateSuccessful;
    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;
        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete(AppConstants.TABLE_STUDENT, "id ='" + id + "'", null) > 0;
        db.close();
        return deleteSuccessful;
    }
}
