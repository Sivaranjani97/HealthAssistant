package com.example.meyyappan.healthassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reports);
        Button upload = (Button)findViewById(R.id.upload);
        Button view =(Button)findViewById(R.id.view);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DoctorReportsActivity.this,Upload.class);
                startActivity(intent1);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorReportsActivity.this,ViewReports.class);
                startActivity(intent);
            }
        });
    }
}