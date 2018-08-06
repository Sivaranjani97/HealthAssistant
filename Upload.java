package com.example.meyyappan.healthassistant;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Upload extends AppCompatActivity {

    EditText editreport, editdoc;
    Button save,record;
    DatabaseReference dbtext;
    ListView listViewReports ;
    List<Report> reportList;
    private final int SPEECH_RECOGNITION_CODE = 1234;
    public static final String PATH = "reports";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        editreport = (EditText) findViewById(R.id.docreport);
        editdoc = (EditText) findViewById(R.id.docname);
        save = (Button) findViewById(R.id.button1);
        listViewReports =(ListView)findViewById(R.id.listViewReports) ;
        reportList = new ArrayList<>();
        record = (Button)findViewById(R.id.record);

        dbtext = FirebaseDatabase.getInstance().getReference(PATH);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordReport();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReport();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        dbtext.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reportList.clear();


                for (DataSnapshot reportSnapshot : dataSnapshot.getChildren()) {

                    Report report = reportSnapshot.getValue(Report.class);

                    reportList.add(report);
                }


                ReportList adapter = new ReportList(Upload.this, reportList);

                listViewReports.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void recordReport(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speak something...");
        try {
            startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Speech recognition is not supported in this device.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SPEECH_RECOGNITION_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = result.get(0);
                    editreport.setText(text);
                }
                break;
            }
        }
    }


    public void addReport() {
        String reportTxt = editreport.getText().toString().trim();
        String name = editdoc.getText().toString().trim();
        if (!TextUtils.isEmpty(reportTxt)) {
            String id = dbtext.push().getKey();
            Report report = new Report(reportTxt, name, id);
            dbtext.child(id).setValue(report);
            Toast.makeText(this, "Report Successfully added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Report cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}

