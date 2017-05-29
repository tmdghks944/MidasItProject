package com.midas.mobile3.mobile3.models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.midas.mobile3.mobile3.Common;
import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.VonuntaryContentActivity;
import com.midas.mobile3.mobile3.db.CompleteDBHelper;
import com.midas.mobile3.mobile3.db.RequestDBHelper;
import com.midas.mobile3.mobile3.db_model.RequestThing;
import com.midas.mobile3.mobile3.db_model.ActiveThing;

/**
 * Created by koohanmo on 2017-05-27.
 */
public class RequestThingHolder extends RecyclerView.ViewHolder{

    RequestThing data;
    TextView txtUserName, txtVSName;
    Button btnSuccess, btnFail;
    Context mcon;

    public RequestThingHolder(View itemView, final Context mcon) {
        super(itemView);
        this.mcon = mcon;
        txtUserName = (TextView)itemView.findViewById(R.id.admin_voluntary_txt_user_name);
        txtVSName = (TextView)itemView.findViewById(R.id.admin_voluntary_txt_voluntary_name);
        btnSuccess = (Button) itemView.findViewById(R.id.admin_voluntary_btn_ok);
        btnFail = (Button) itemView.findViewById(R.id.admin_voluntary_btn_fail);

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestDBHelper rdbh = new RequestDBHelper(mcon);
                rdbh.delete(data.requestCode);

                CompleteDBHelper cdbh = new CompleteDBHelper(mcon);
                cdbh.insert(data.user.userCode, data.voluntary.voluntaryCode);
                btnFail.setVisibility(View.GONE);
                btnSuccess.setVisibility(View.GONE);
            }
        });

        btnFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestDBHelper rdbh = new RequestDBHelper(mcon);
                rdbh.delete(data.requestCode);
                btnFail.setVisibility(View.GONE);
                btnSuccess.setVisibility(View.GONE);
            }
        });
    }

    public void setData(RequestThing data){
        this.data = data;
        txtUserName.setText(data.user.userName);
        txtVSName.setText(data.voluntary.voluntaryTitle);
    }
}