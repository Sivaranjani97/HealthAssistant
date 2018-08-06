package com.example.meyyappan.healthassistant;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by meyyappan on 2/19/2018.
 */

public class ReportList extends ArrayAdapter<Report> {
    private Activity context;
    List<Report> reportList;

    public ReportList(Activity context, List<Report> reportList) {
        super(context, R.layout.list_layout, reportList);
        this.context = context;
        this.reportList = reportList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewReport = (TextView) listViewItem.findViewById(R.id.textViewReport);

        Report report = reportList.get(position);
        textViewName.setText(report.getDocname());
        textViewReport.setText(report.getDocreport());

        return listViewItem;
    }
}
