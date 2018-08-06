package com.example.meyyappan.healthassistant;

/**
 * Created by meyyappan on 2/19/2018.
 */

class Report {
    String docreport;
    String docname;
    String reportid;

    public Report(String docreport, String docname, String reportid) {
        this.docreport = docreport;
        this.docname = docname;
        this.reportid = reportid;
    }

    public Report() {
    }

    public String getDocreport() {
        return docreport;
    }

    public String getDocname() {
        return docname;
    }

    public String getReportid() {
        return reportid;
    }
}
