package com.midas.mobile3.mobile3.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.midas.mobile3.mobile3.db.BusinessDBHelper;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;
import com.midas.mobile3.mobile3.db_model.PointThing;
import com.midas.mobile3.mobile3.db_model.Voluntary;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by koohanmo on 2017-05-27.
 */
public class PointThingHolder extends RecyclerView.ViewHolder{

    PointThing data;
    TextView txtDate, txtPoint, txtName;
    Context mcon;

    public PointThingHolder(View itemView, final Context mcon) {
        super(itemView);
        this.mcon = mcon;
        txtDate = (TextView)itemView.findViewById(R.id.point_recycler_item_date);
        txtPoint = (TextView)itemView.findViewById(R.id.point_recycler_item_point);
        txtName=(TextView)itemView.findViewById(R.id.point_recycler_item_name);
    }

    public void setData(PointThing data){
        this.data = data;

        if( data.sort == 1 ){
            VoluntaryDBHelper vdbh = new VoluntaryDBHelper(mcon);
            txtDate.setText(Common.dateToString(data.date));
            txtName.setText(vdbh.selectVoluntaryInfo(data.code).voluntaryTitle);

            txtPoint.setText("+ "+data.point+"P");
            txtPoint.setTextColor(Color.parseColor("#0000FF"));
        }
        else{
            BusinessDBHelper bdbh = new BusinessDBHelper(mcon);
            txtDate.setText(Common.dateToString(data.date));
            txtName.setText(bdbh.selectBusiness(data.code).businessName);

            txtPoint.setText("- "+data.point+"P");
            txtPoint.setTextColor(Color.parseColor("#FF0000"));
        }
    }
}