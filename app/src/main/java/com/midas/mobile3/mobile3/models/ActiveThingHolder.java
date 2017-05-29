package com.midas.mobile3.mobile3.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.midas.mobile3.mobile3.Common;
import com.midas.mobile3.mobile3.MainActivity;
import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.VoluntaryFragment;
import com.midas.mobile3.mobile3.VonuntaryContentActivity;
import com.midas.mobile3.mobile3.db_model.ActiveThing;
import com.midas.mobile3.mobile3.db_model.Voluntary;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by koohanmo on 2017-05-27.
 */
public class ActiveThingHolder extends RecyclerView.ViewHolder{

    ActiveThing data;
    TextView txtTitle, txtDate, txtPoint;
    ImageView img;
    Context mcon;

    public ActiveThingHolder(View itemView, final Context mcon) {
        super(itemView);
        this.mcon = mcon;
        txtTitle = (TextView)itemView.findViewById(R.id.voluntary_recycler_item_title);
        txtDate = (TextView)itemView.findViewById(R.id.voluntary_recycler_item_date);
        txtPoint = (TextView)itemView.findViewById(R.id.voluntary_recycler_item_point);
        img = (ImageView)itemView.findViewById(R.id.voluntary_recycler_item_img);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcon, VonuntaryContentActivity.class);
                i.putExtra("data",data.voluntary);
                mcon.startActivity(i);
            }
        });
    }

    public void setData(ActiveThing data){
        this.data = data;
        txtTitle.setText(data.voluntary.voluntaryTitle);
        txtDate.setText(Common.dateToString(data.voluntary.voluntaryReqStartDate) + " ~ " + Common.dateToString(data.voluntary.voluntaryReqEndDate));
        txtPoint.setText(data.voluntary.voluntaryPoint+"P");
        Glide.with(mcon).load(data.voluntary.voluntaryImg).into(img);

        if( data.sort == 2 ){

        }
    }
}