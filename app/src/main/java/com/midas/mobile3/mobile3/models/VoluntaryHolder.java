package com.midas.mobile3.mobile3.models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.midas.mobile3.mobile3.Common;
import com.midas.mobile3.mobile3.MainActivity;
import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.VonuntaryContentActivity;
import com.midas.mobile3.mobile3.db_model.Voluntary;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import jp.wasabeef.picasso.transformations.BlurTransformation;


/**
 * Created by koohanmo on 2017-05-27.
 */
public class VoluntaryHolder extends RecyclerView.ViewHolder{

    Voluntary data;
    TextView txtTitle, txtDate, txtPoint;
    ImageView img;
    Context mcon;

    public VoluntaryHolder(final View itemView, final Context mcon) {
        super(itemView);
        this.mcon = mcon;
        txtTitle = (TextView)itemView.findViewById(R.id.voluntary_recycler_item_title);
        txtDate = (TextView)itemView.findViewById(R.id.voluntary_recycler_item_date);
        txtPoint = (TextView)itemView.findViewById(R.id.voluntary_recycler_item_point);
        img = (ImageView)itemView.findViewById(R.id.voluntary_recycler_item_img);

        //여기서 봉사활동의 id가 정해진다.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcon, VonuntaryContentActivity.class);
                i.putExtra("data",data);
                mcon.startActivity(i);
            }
        });
    }

    public void setData(Voluntary data){
        this.data = data;
        txtTitle.setText(data.voluntaryTitle);
        txtDate.setText(Common.dateToString(data.voluntaryReqStartDate) + " ~ " + Common.dateToString(data.voluntaryReqEndDate));
        txtPoint.setText(data.voluntaryPoint+"P");
        if(data.voluntaryImg!=null && !data.voluntaryImg.equals(""))
            Picasso.with(mcon).load(data.voluntaryImg).into(img);
    }
}