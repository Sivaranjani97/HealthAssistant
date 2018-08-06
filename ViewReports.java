package com.example.meyyappan.healthassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewReports extends AppCompatActivity {

    List<Report> reportList;
    DatabaseReference dbtext;
    ListView listViewReports ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);
        reportList = new ArrayList<>();
        dbtext = FirebaseDatabase.getInstance().getReference("reports");
        listViewReports =(ListView)findViewById(R.id.listViewReports) ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbtext.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reportList.clear();

                //iterating through all the nodes
                for (DataSnapshot reportSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Report report = reportSnapshot.getValue(Report.class);
                    //adding artist to the list
                    reportList.add(report);
                }
                ReportList adapter = new ReportList(ViewReports.this, reportList);
                listViewReports.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
