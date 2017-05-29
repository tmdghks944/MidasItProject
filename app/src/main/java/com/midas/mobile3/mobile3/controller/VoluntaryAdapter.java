package com.midas.mobile3.mobile3.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;
import com.midas.mobile3.mobile3.db_model.Voluntary;
import com.midas.mobile3.mobile3.models.VoluntaryHolder;

import java.util.ArrayList;

/**
 * Created by myRoom on 2017-05-27.
 */

public class VoluntaryAdapter extends RecyclerView.Adapter<VoluntaryHolder>{

    ArrayList<Voluntary> voluntaryList;
    Context mcon;

    public VoluntaryAdapter(Context mcon){
        this.mcon=mcon;
        // 마감임박순 -> 신청기간 안지난것만
        updateDataset();
        /*if( this.voluntaryList != null ){
            for(int i=0; i<this.voluntaryList.size(); i++){
                System.out.println(this.voluntaryList.get(i));
            }
        }*/
    }


    @Override
    public VoluntaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcon).inflate(R.layout.item_voluntary,parent,false);
        return new VoluntaryHolder(v, mcon);
    }

    @Override
    public void onBindViewHolder(VoluntaryHolder holder, int position) {
        holder.setData(voluntaryList.get(position));
    }

    @Override
    public int getItemCount() {
        if( this.voluntaryList == null ) {
            voluntaryList = new ArrayList<Voluntary>();
        }

        return voluntaryList.size();
    }

    public void updateDataset(){
        VoluntaryDBHelper vdbh  = new VoluntaryDBHelper(mcon);
        this.voluntaryList = vdbh.selectVoluntaryInfoIng();
    }
}
