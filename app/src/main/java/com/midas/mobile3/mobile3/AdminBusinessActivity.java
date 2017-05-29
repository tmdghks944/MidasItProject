package com.midas.mobile3.mobile3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midas.mobile3.mobile3.R;
import com.midas.mobile3.mobile3.db.BusinessDBHelper;

public class AdminBusinessActivity extends AppCompatActivity {

    EditText editTitle, editPrice, editURL, editContents;
    Button btnComplete;
    Context mCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_business);
        setLayout();
        mCon=this;
    }

    private void setLayout(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("사업 추가");
        setSupportActionBar(toolbar);

        editTitle=(EditText)findViewById(R.id.admin_edit_titlename);
        editPrice=(EditText)findViewById(R.id.admin_edit_price);
        editURL=(EditText)findViewById(R.id.admin_edit_url);
        editContents=(EditText)findViewById(R.id.admin_edit_contents);

        btnComplete =(Button)findViewById(R.id.admin_btn);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( editTitle.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "사업 이름을 입력하세요.");
                }
                else if( editContents.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "상세 내용을 입력하세요.");
                }
                else if( editPrice.getText().toString().trim().equals("")){
                    Common.makeToast(mCon, "목표 금액을 입력하세요.");
                }
                else{
                    String imgUrl = editURL.getText().toString().trim();

                    if( imgUrl == null || imgUrl.equals("")){
                        imgUrl = "http://cfile6.uf.tistory.com/image/2120913D58B0C2F1297897";
                    }

                    String name = editTitle.getText().toString().trim();
                    String content = editContents.getText().toString().trim();
                    String priceStr = editPrice.getText().toString().trim();

                    int price = Integer.parseInt(priceStr);

                    BusinessDBHelper bdbh = new BusinessDBHelper(mCon);
                    bdbh.insert(name, content, price, 0, imgUrl);

                    Toast.makeText(mCon,"성공적으로 등록되었습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

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
