package com.midas.mobile3.mobile3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.midas.mobile3.mobile3.controller.ReportContentAdapter;
import com.midas.mobile3.mobile3.db_model.ReportThing;
import com.midas.mobile3.mobile3.db_model.Business;
import com.midas.mobile3.mobile3.db_model.ReportThing;

import java.util.ArrayList;

public class ReportContentsActivity extends AppCompatActivity {

    Context mcon;
    ArrayList<String> imgUrlList;
    ReportThing data;
    TextView txtTitle, txtPoint, txtContents;

    RecyclerView mRecyclerView;
    ReportContentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcon = this;
        data = (ReportThing) getIntent().getSerializableExtra("data");
        setLayout();
    }

    private void setLayout(){

        setContentView(R.layout.activity_report_contents);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("사업 결과");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTitle = (TextView)findViewById(R.id.report_contents_title);
        txtTitle.setText(data.business.businessName);

        txtPoint = (TextView)findViewById(R.id.report_contents_point);
        txtPoint.setText(data.business.businessGoalPoint+"원");

        txtContents = (TextView)findViewById(R.id.report_contents_contents);
        txtContents.setText(data.reportContent.trim());

        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mAdapter = new ReportContentAdapter(this, data.reportImgUrlList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter);
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
