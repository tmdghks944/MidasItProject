package com.midas.mobile3.mobile3.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midas.mobile3.mobile3.Common;
import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.db.CompleteDBHelper;
import com.midas.mobile3.mobile3.db.DonationDBHelper;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;
import com.midas.mobile3.mobile3.db_model.Complete;
import com.midas.mobile3.mobile3.db_model.Donation;
import com.midas.mobile3.mobile3.db_model.PointThing;
import com.midas.mobile3.mobile3.models.ActiveThingHolder;
import com.midas.mobile3.mobile3.models.PointThingHolder;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by myRoom on 2017-05-27.
 */

public class PointThingAdapter extends RecyclerView.Adapter<PointThingHolder>{

    ArrayList<PointThing> pointThingList;
    Context mcon;

    public PointThingAdapter(Context mcon){
        this.mcon = mcon;

        pointThingList = new ArrayList<PointThing>();

        CompleteDBHelper cdbh = new CompleteDBHelper(mcon);
        ArrayList<Complete> completeList = cdbh.selectComplete(Common.userCode);

        if( completeList != null ){
            VoluntaryDBHelper vdbh = new VoluntaryDBHelper(mcon);
            PointThing pointThing = null;

            for(int i=0; i<completeList.size(); i++){
                pointThing = new PointThing();

                pointThing.code = completeList.get(i).vsCode;
                pointThing.sort = 1;
                pointThing.date = completeList.get(i).completeDate;
                pointThing.point = vdbh.selectVoluntaryInfo(completeList.get(i).vsCode).voluntaryPoint;

                pointThingList.add(pointThing);
            }
        }

        DonationDBHelper ddbh = new DonationDBHelper(mcon);
        ArrayList<Donation> donationList =  ddbh.selectDonation(Common.userCode);

        if( donationList != null ){
            PointThing pointThing = null;

            for(int i=0; i<donationList.size(); i++){
                pointThing = new PointThing();

                pointThing.code = donationList.get(i).businessCode;
                pointThing.sort = 2;
                pointThing.date = donationList.get(i).donationDate;
                pointThing.point = donationList.get(i).donationPoint;

                pointThingList.add(pointThing);
            }
        }

        // activeThingList.voluntary

        if( pointThingList != null ){
            Collections.sort(pointThingList, new Comparator<PointThing>() {
                @Override
                public int compare(PointThing o1, PointThing o2) {
                    if( o1.date.getTime() < o2.date.getTime() ){
                        return 1;
                    }
                    else if( o1.date.getTime() > o2.date.getTime() ){
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            });
        }
    }


    @Override
    public PointThingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcon).inflate(R.layout.item_point,parent,false);
        return new PointThingHolder(v, mcon);
    }

    @Override
    public void onBindViewHolder(PointThingHolder holder, int position) {
        holder.setData(pointThingList.get(position));
    }

    @Override
    public int getItemCount() {
        if( this.pointThingList == null ) {
            pointThingList = new ArrayList<PointThing>();
        }

        return pointThingList.size();
    }
}
