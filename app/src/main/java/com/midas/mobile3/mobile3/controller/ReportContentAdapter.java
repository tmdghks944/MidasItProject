package com.midas.mobile3.mobile3.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.db.CompleteDBHelper;
import com.midas.mobile3.mobile3.db.DonationDBHelper;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;
import com.midas.mobile3.mobile3.db_model.Complete;
import com.midas.mobile3.mobile3.db_model.Donation;
import com.midas.mobile3.mobile3.db_model.PointThing;
import com.midas.mobile3.mobile3.models.PointThingHolder;
import com.midas.mobile3.mobile3.models.ReportContentHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by myRoom on 2017-05-27.
 */

public class ReportContentAdapter extends RecyclerView.Adapter<ReportContentHolder>{

    ArrayList<String> imgList;
    Context mcon;

    public ReportContentAdapter(Context mcon, ArrayList imgLIst){
        this.mcon = mcon;
        this.imgList = imgLIst;
    }


    @Override
    public ReportContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcon).inflate(R.layout.item_report_contents,parent,false);
        return new ReportContentHolder(v, mcon);
    }

    @Override
    public void onBindViewHolder(ReportContentHolder holder, int position) {
        holder.setData(imgList.get(position));
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }
}
