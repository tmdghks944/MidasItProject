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
import com.midas.mobile3.mobile3.db.UserDBHelper;
import com.midas.mobile3.mobile3.db_model.RequestThing;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;
import com.midas.mobile3.mobile3.db_model.ActiveThing;
import com.midas.mobile3.mobile3.db_model.Complete;
import com.midas.mobile3.mobile3.db_model.Request;
import com.midas.mobile3.mobile3.models.RequestThingHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by myRoom on 2017-05-27.
 */

public class AdminVoluntaryAdapter extends RecyclerView.Adapter<RequestThingHolder>{

    ArrayList<RequestThing> requestThingList;
    Context mcon;

    public AdminVoluntaryAdapter(Context mcon){
        this.mcon=mcon;
        updateDataset();
    }


    @Override
    public RequestThingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcon).inflate(R.layout.item_admin_voluntary,parent,false);
        return new RequestThingHolder(v, mcon);
    }

    @Override
    public void onBindViewHolder(RequestThingHolder holder, int position) {
        holder.setData(requestThingList.get(position));
    }

    @Override
    public int getItemCount() {
        if( this.requestThingList == null ) {
            requestThingList = new ArrayList<RequestThing>();
        }

        return requestThingList.size();
    }

    public void updateDataset(){
        RequestDBHelper rdbh  = new RequestDBHelper(mcon);
        ArrayList<Request> requestList = rdbh.selectCompleteRequest();

        if( requestList != null ){
            requestThingList = new ArrayList<RequestThing>();
            RequestThing node = null;

            VoluntaryDBHelper vdbh = new VoluntaryDBHelper(mcon);
            UserDBHelper udbh = new UserDBHelper(mcon);

            for(int i=0; i<requestList.size(); i++){
                node = new RequestThing();

                node.requestCode = requestList.get(i).requestCode;
                node.user = udbh.selectUserInfo(requestList.get(i).userCode);
                node.voluntary = vdbh.selectVoluntaryInfo(requestList.get(i).vsCode);

                requestThingList.add(node);
            }
        }
    }
}
