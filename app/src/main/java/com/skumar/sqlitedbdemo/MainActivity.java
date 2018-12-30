package com.skumar.sqlitedbdemo;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonCreateStudent = (Button) findViewById(R.id.buttonCreateStudent);
        buttonCreateStudent.setOnClickListener(new OnClickListenerCreateStudent());
        countRecords();
        readRecords();
    }

    public void countRecords() {
        int recordCount = new TableControllerStudent(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }

    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();
        List<Student> students = new TableControllerStudent(this).read();
        if (students.size() > 0) {
            for (Student obj : students) {
                int id = obj.id;
                String studentFirstname = obj.studentName;
                String studentEmail = obj.email;
                String textViewContents = studentFirstname + " - " + studentEmail;
                TextView textViewStudentItem = new TextView(this);
                textViewStudentItem.setTextColor(getResources().getColor(android.R.color.black));
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));
                linearLayoutRecords.addView(textViewStudentItem);
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentRecord());
            }
        } else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");
            linearLayoutRecords.addView(locationItem);
        }
    }
}
