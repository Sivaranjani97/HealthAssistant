package com.example.meyyappan.healthassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button testReports,doctorReports,remainder,healthNews,bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        testReports = (Button)findViewById(R.id.testReports);
        doctorReports = (Button)findViewById(R.id.doctorReports);
        remainder = (Button)findViewById(R.id.appointment);
        healthNews = (Button)findViewById(R.id.healthNews);

        bmi=(Button) findViewById(R.id.bmi);

        testReports.setOnClickListener(this);
        doctorReports.setOnClickListener(this);
        remainder.setOnClickListener(this);
        healthNews.setOnClickListener(this);
        bmi.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())){
           case R.id.testReports:
                startActivity((new Intent(this,TestReportsActivity.class)));
                break;
            case R.id.doctorReports:
                startActivity((new Intent(this,DoctorReportsActivity.class)));
                break;
            case R.id.appointment:
                  startActivity((new Intent(this,Appointment.class)));
                break;
            case R.id.healthNews:
                startActivity((new Intent(this,HealthNewsActivity.class)));
                break;
            case R.id.bmi:
                startActivity((new Intent(this,BmiActivity.class)));
                break;



        }
    }
}
