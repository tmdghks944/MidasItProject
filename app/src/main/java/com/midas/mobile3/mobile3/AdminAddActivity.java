package com.midas.mobile3.mobile3;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midas.mobile3.mobile3.controller.PointThingAdapter;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminAddActivity extends AppCompatActivity {

    EditText editTitle, editReqStart,editReqEnd,editExcStart,editExcEnd,editPrice, editURL, editContents;
    Button btnComplete;
    Context mCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);
        mCon = this;
        setLayout();
    }

    private void setLayout(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("봉사활동 추가");
        setSupportActionBar(toolbar);

        editTitle=(EditText)findViewById(R.id.admin_edit_titlename);
        editPrice=(EditText)findViewById(R.id.admin_edit_price);
        editReqStart=(EditText)findViewById(R.id.admin_edit_req_start);
        editReqEnd=(EditText)findViewById(R.id.admin_edit_req_end);
        editExcStart=(EditText)findViewById(R.id.admin_edit_req_start);
        editExcEnd=(EditText)findViewById(R.id.admin_edit_req_end);

        editURL=(EditText)findViewById(R.id.admin_edit_url);
        editContents=(EditText)findViewById(R.id.admin_edit_contents);

        btnComplete =(Button)findViewById(R.id.admin_btn);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( editTitle.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "봉사 이름을 입력하세요.");
                }
                else if( editContents.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "상세 내용을 입력하세요.");
                }
                else if( editPrice.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "금액을 입력하세요.");
                }
                else if( editReqStart.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "공고 시작날짜를 입력하세요.");
                }
                else if( editReqEnd.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "공고 종료날짜를 입력하세요.");
                }
                else if( editExcStart.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "봉사 시작날짜를 입력하세요.");
                }
                else if( editExcEnd.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "봉사 종료날짜를 입력하세요.");
                }
                else{
                    String imgUrl = editURL.getText().toString().trim();

                    if( imgUrl == null || imgUrl.equals("")){
                        imgUrl = "http://cfile6.uf.tistory.com/image/2120913D58B0C2F1297897";
                    }

                    String name = editTitle.getText().toString().trim();
                    String content = editContents.getText().toString().trim();
                    String reqStart = editReqStart.getText().toString().trim();
                    String reqEnd = editReqEnd.getText().toString().trim();
                    String exStart = editExcStart.getText().toString().trim();
                    String exEnd = editExcEnd.getText().toString().trim();


                    String priceStr = editPrice.getText().toString().trim();
                    int price = Integer.parseInt(priceStr);

                    VoluntaryDBHelper vdbh = new VoluntaryDBHelper(mCon);
                    vdbh.insert(name, stringToTimestamp(reqStart), stringToTimestamp(reqEnd), stringToTimestamp(exStart), stringToTimestamp(exEnd), price, content, 1, imgUrl);

                    Toast.makeText(mCon,"성공적으로 등록되었습니다.",Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }

    private Timestamp stringToTimestamp(String str){
        Timestamp reqStartTs = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date parsedDate = dateFormat.parse(str);
            reqStartTs = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        return reqStartTs;
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
