package com.midas.mobile3.mobile3.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.db.BusinessDBHelper;
import com.midas.mobile3.mobile3.db_model.Business;
import com.midas.mobile3.mobile3.models.BusinessHolder;

import java.util.ArrayList;

/**
 * Created by myRoom on 2017-05-27.
 */

public class BusinessAdapter extends RecyclerView.Adapter<BusinessHolder>{

    ArrayList<Business> businessList;
    Context mcon;

    public BusinessAdapter(Context mcon){
        this.mcon=mcon;
        // cur point 순
        BusinessDBHelper bdbh  = new BusinessDBHelper(mcon);
        this.businessList = bdbh.selectBusinessIng();
    }


    @Override
    public BusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcon).inflate(R.layout.item_business,parent,false);
        return new BusinessHolder(v, mcon);
    }

    @Override
    public void onBindViewHolder(BusinessHolder holder, int position) {
        holder.setData(businessList.get(position));
    }

    @Override
    public int getItemCount() {
        if( this.businessList== null ) {
            businessList = new ArrayList<Business>();
        }

        return businessList.size();
    }

    public void updateDataset(){
        BusinessDBHelper bdbh  = new BusinessDBHelper(mcon);
        this.businessList = bdbh.selectBusinessIng();
    }
}
