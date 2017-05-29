package com.midas.mobile3.mobile3;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.midas.mobile3.mobile3.db.CompleteDBHelper;
import com.midas.mobile3.mobile3.db.RequestDBHelper;
import com.midas.mobile3.mobile3.db.UserDBHelper;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;
import com.midas.mobile3.mobile3.db.RequestDBHelper;
import com.midas.mobile3.mobile3.db_model.Complete;
import com.midas.mobile3.mobile3.db_model.Request;
import com.midas.mobile3.mobile3.db_model.Voluntary;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class VonuntaryContentActivity extends AppCompatActivity {

    Context mcon;
    Voluntary data;
    ImageView imgTitle;
    TextView txtTitle, txtExtDate, txtReqDate, txtPoint, txtContents;
    Button btnRequest;

    int status = 0; // 0 : 신청가능, 1 : 이미 신청 중, 2 : 완료한 것

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcon = this;
        data = (Voluntary)getIntent().getSerializableExtra("data");

        RequestDBHelper rdbh = new RequestDBHelper(mcon);
        ArrayList<Request> requestList = rdbh.selectRequest(Common.userCode, data.voluntaryCode);

        if(requestList != null && requestList.size() > 0){
           status = 1;
        }

        CompleteDBHelper cdbh = new CompleteDBHelper(mcon);
        ArrayList<Complete> completeList = cdbh.selectComplete(Common.userCode, data.voluntaryCode);

        if(completeList != null && completeList.size() > 0){
            status = 2;
        }

        setLayout();
    }

    private void setLayout(){

        setContentView(R.layout.activity_vonuntary_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("봉사 활동");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgTitle = (ImageView)findViewById(R.id.voluntary_contents_img_title);
        if(data.voluntaryImg!=null && !data.voluntaryImg.equals(""))
            Picasso.with(this).load(data.voluntaryImg).into(imgTitle);

        txtTitle = (TextView)findViewById(R.id.voluntary_contents_title);
        txtTitle.setText(data.voluntaryTitle);

        txtExtDate = (TextView)findViewById(R.id.voluntary_contents_excdate);
        txtExtDate.setText(Common.dateToString(data.voluntaryExcStartDate) + " ~ "+ Common.dateToString(data.voluntaryExcEndDate));

        txtReqDate = (TextView)findViewById(R.id.voluntary_contents_reqdate);
        txtReqDate.setText(Common.dateToString(data.voluntaryReqStartDate) + "~" + Common.dateToString(data.voluntaryReqEndDate));

        txtPoint = (TextView)findViewById(R.id.voluntary_contents_point);
        txtPoint.setText(data.voluntaryPoint+"P");

        txtContents = (TextView)findViewById(R.id.voluntary_contents_contents);
        txtContents.setText(data.voluntaryContent.trim());

        btnRequest = (Button) findViewById(R.id.voluntary_contents_request);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestVoluntary();
            }
        });

        //여기서 봉사활동 신청.
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestVoluntary();
            }
        });

        prepareRequestVoluntary();
    }

    private void prepareRequestVoluntary(){
        if(status == 0){ // 신청가능
            fab.setImageResource(R.drawable.ic_add_black_24dp);
            btnRequest.setText("봉사활동 신청");
        }
        else if(status == 1){ // 이미 신청되어있음
            fab.setImageResource(R.drawable.ic_remove_black_24dp);
            btnRequest.setText("봉사활동 취소");
        }
        else if(status == 2){ // 완료된 봉사활동
            fab.setVisibility(View.INVISIBLE);
            btnRequest.setText("이미 완료한 봉사활동");
            btnRequest.setClickable(false);
        }
    }

    private void requestVoluntary(){
        if(status == 0){ // 신청가능
            //봉사활동 신청이 안되어 있을 경우
            VoluntaryDBHelper vdbh = new VoluntaryDBHelper(mcon);
            Voluntary voluntary = vdbh.selectVoluntaryInfo(data.voluntaryCode);

            RequestDBHelper rdbh = new RequestDBHelper(mcon);
            rdbh.insert(Common.userCode, data.voluntaryCode, voluntary.voluntaryExcEndDate);

            Snackbar.make(fab, data.voluntaryTitle + " 신청이 완료되었습니다.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            fab.setImageResource(R.drawable.ic_remove_black_24dp);
            btnRequest.setText("봉사활동 취소");

            status = 1;
        }
        else if(status == 1){ // 이미 신청되어있음
            RequestDBHelper rdbh = new RequestDBHelper(mcon);
            rdbh.delete(Common.userCode, data.voluntaryCode);

            Snackbar.make(fab, data.voluntaryTitle + " 취소가 완료되었습니다.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            fab.setImageResource(R.drawable.ic_add_black_24dp);
            btnRequest.setText("봉사활동 신청");

            status = 0;
        }
        else if(status == 2){ // 완료된 봉사활동
            fab.setVisibility(View.INVISIBLE);
            btnRequest.setText("이미 완료한 봉사활동");
            btnRequest.setClickable(false);
        }

        if( MainActivity.voluntaryCheckFragment != null){
            MainActivity.voluntaryCheckFragment.updateDataset();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
