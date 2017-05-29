package com.midas.mobile3.mobile3.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midas.mobile3.mobile3.Common;
import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.db.CompleteDBHelper;
import com.midas.mobile3.mobile3.db.RequestDBHelper;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;
import com.midas.mobile3.mobile3.db_model.ActiveThing;
import com.midas.mobile3.mobile3.db_model.Complete;
import com.midas.mobile3.mobile3.db_model.Request;
import com.midas.mobile3.mobile3.models.ActiveThingHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by myRoom on 2017-05-27.
 */

public class ActiveThingAdapter extends RecyclerView.Adapter<ActiveThingHolder>{

    ArrayList<ActiveThing> activeThingList;
    Context mcon;

    public ActiveThingAdapter(Context mcon){
        this.mcon=mcon;
        updateDataset();
    }


    @Override
    public ActiveThingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcon).inflate(R.layout.item_voluntary,parent,false);
        return new ActiveThingHolder(v, mcon);
    }

    @Override
    public void onBindViewHolder(ActiveThingHolder holder, int position) {
        holder.setData(activeThingList.get(position));
    }

    @Override
    public int getItemCount() {
        if( this.activeThingList == null ) {
            activeThingList = new ArrayList<ActiveThing>();
        }

        return activeThingList.size();
    }

    public void updateDataset(){
        activeThingList = new ArrayList<ActiveThing>();

        RequestDBHelper rdbh  = new RequestDBHelper(mcon);
        ArrayList<Request> requestList  = rdbh.selectRequest(Common.userCode);

        if( requestList != null ){
            VoluntaryDBHelper vdbh = new VoluntaryDBHelper(mcon);
            ActiveThing activeThing = null;

            for(int i=0; i<requestList.size(); i++){
                activeThing = new ActiveThing();

                activeThing.code = requestList.get(i).requestCode;
                activeThing.sort = requestList.get(i).requestSort;
                activeThing.date = requestList.get(i).requestDate;
                activeThing.voluntary = vdbh.selectVoluntaryInfo(requestList.get(i).vsCode);

                activeThingList.add(activeThing);
            }
        }

        CompleteDBHelper cdbh = new CompleteDBHelper(mcon);
        ArrayList<Complete> completeList = cdbh.selectComplete(Common.userCode);

        if( completeList != null ){
            VoluntaryDBHelper vdbh = new VoluntaryDBHelper(mcon);
            ActiveThing activeThing = null;

            for(int i=0; i<completeList.size(); i++){
                activeThing = new ActiveThing();

                activeThing.code = completeList.get(i).completeCode;
                activeThing.sort = 2;
                activeThing.date = completeList.get(i).completeDate;
                activeThing.voluntary = vdbh.selectVoluntaryInfo(completeList.get(i).vsCode);

                activeThingList.add(activeThing);
            }
        }

        if( activeThingList == null ){
        }
        else{
            Collections.sort(activeThingList, new Comparator<ActiveThing>() {
                @Override
                public int compare(ActiveThing o1, ActiveThing o2) {
                    if( o1.date.getTime() > o2.date.getTime() ){
                        return 1;
                    }
                    else if( o1.date.getTime() < o2.date.getTime() ){
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            });
        }
    }
}
