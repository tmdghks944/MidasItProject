package com.midas.mobile3.mobile3.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midas.mobile3.mobile3.Common;
import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.db.BusinessDBHelper;
import com.midas.mobile3.mobile3.db.ReportDBHelper;
import com.midas.mobile3.mobile3.db_model.Report;
import com.midas.mobile3.mobile3.db_model.ReportThing;
import com.midas.mobile3.mobile3.models.ReportThingHolder;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by myRoom on 2017-05-27.
 */

public class ReportThingAdapter extends RecyclerView.Adapter<ReportThingHolder>{

    ArrayList<ReportThing> reportThingList;
    Context mcon;

    public ReportThingAdapter(Context mcon){
        this.mcon=mcon;
        updateDataset();
    }


    @Override
    public ReportThingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcon).inflate(R.layout.item_voluntary,parent,false);
        return new ReportThingHolder(v, mcon);
    }

    @Override
    public void onBindViewHolder(ReportThingHolder holder, int position) {
        holder.setData(reportThingList.get(position));
    }

    @Override
    public int getItemCount() {
        if( this.reportThingList == null ) {
            reportThingList = new ArrayList<ReportThing>();
        }

        return reportThingList.size();
    }

    public void updateDataset(){
        reportThingList = new ArrayList<ReportThing>();

        ReportDBHelper rdbh  = new ReportDBHelper(mcon);
        ArrayList<Report> reportList  = rdbh.selectReport();

        if( reportList != null ){
            BusinessDBHelper bdbh = new BusinessDBHelper(mcon);
            ReportThing reportThing = null;

            for(int i=0; i<reportList.size(); i++){
                reportThing = new ReportThing();

                reportThing.reportCode = reportList.get(i).reportCode;
                reportThing.reportContent = reportList.get(i).reportContent;
                reportThing.reportImgUrlList = reportList.get(i).reportImgUrlList;
                reportThing.business = bdbh.selectBusiness(reportList.get(i).businessCode);

                reportThingList.add(reportThing);
            }
        }
    }
}
