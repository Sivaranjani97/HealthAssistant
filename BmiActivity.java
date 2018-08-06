package com.example.meyyappan.healthassistant;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class BmiActivity extends AppCompatActivity {


    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


        final EditText height = (EditText) findViewById(R.id.height_input);
        final EditText weight = (EditText) findViewById(R.id.weight_input);
        final TextView bmi_result = (TextView) findViewById(R.id.bmi_result);
        Button button = (Button) findViewById(R.id.bmi_calc_button);
        final TextView bmi_comment = (TextView)findViewById(R.id.bmi_comment);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (height.getText().toString().length() < 1) {
                    Toast.makeText(getApplicationContext(), "enter your height", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (weight.getText().toString().length() < 1) {
                    Toast.makeText(getApplicationContext(), "enter your weight", Toast.LENGTH_SHORT).show();

                    return;
                }

                float h = Float.valueOf(height.getText().toString());
                float w = Float.valueOf(weight.getText().toString());


                float BMI = w / (h * h) * 10000;


                bmi_result.setText(String.valueOf(BMI));
                if(BMI<18.5)
                {
                    bmi_comment.setText("You Are Underweight");
                }
               else if(BMI>18.5&&BMI<24.9)
                {
                    bmi_comment.setText("Your are Perfectly Fit");


                }
                else if(BMI>25&&BMI<29.9)
                {
                    bmi_comment.setText("Its time to watch your Health!!! Your Are Overweight");
                }
                else {

                    bmi_comment.setText("Red Alert!!! Obesity Detected");
                }


            }
        });
    }
}



