package com.midas.mobile3.mobile3.models;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.midas.mobile3.mobile3.R;
import com.squareup.picasso.Picasso;

/**
 * Created by myRoom on 2017-05-28.
 */

public class ReportContentHolder extends RecyclerView.ViewHolder{

    String data;
    ImageView img;
    Context mcon;

    public ReportContentHolder(View itemView, final Context mcon) {
        super(itemView);
        this.mcon = mcon;
        img = (ImageView) itemView.findViewById(R.id.img);
    }

    public void setData(String data){
        this.data=data;
        if(data!=null && !data.equals("")){
            Picasso.with(mcon).load(data).into(img);
        }
    }
}