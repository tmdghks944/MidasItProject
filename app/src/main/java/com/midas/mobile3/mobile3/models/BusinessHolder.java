package com.midas.mobile3.mobile3.models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.midas.mobile3.mobile3.BusinessActivity;
import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.VonuntaryContentActivity;
import com.midas.mobile3.mobile3.db_model.Business;
import com.midas.mobile3.mobile3.db_model.Voluntary;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by koohanmo on 2017-05-27.
 */
public class BusinessHolder extends RecyclerView.ViewHolder{

    Business data;
    TextView txtTitle, txtPoint;
    ImageView img;

    Context mcon;

    public BusinessHolder(View itemView, final Context mcon) {
        super(itemView);
        this.mcon = mcon;
        txtTitle = (TextView)itemView.findViewById(R.id.business_recycler_item_title);
        txtPoint = (TextView)itemView.findViewById(R.id.business_recycler_item_point);
        img = (ImageView)itemView.findViewById(R.id.business_recycler_item_img);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcon, BusinessActivity.class);
                i.putExtra("data",data);
                mcon.startActivity(i);
            }
        });
    }

    public void setData(Business data){
        this.data = data;
        txtTitle.setText(data.businessName);
        txtPoint.setText(data.businessCurPoint+ " 원 /  " + data.businessGoalPoint+"원");
        Glide.with(mcon).load(data.businessImgUrl).into(img);
    }
}