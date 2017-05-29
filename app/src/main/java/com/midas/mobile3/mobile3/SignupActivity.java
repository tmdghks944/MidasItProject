package com.midas.mobile3.mobile3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midas.mobile3.mobile3.db.UserDBHelper;
import com.midas.mobile3.mobile3.db_model.User;

public class SignupActivity extends AppCompatActivity {
    Context mcon;

    Button signUpbutton;
    EditText signUpname;
    EditText signUpid;
    EditText signUppassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mcon = this;

        signUpbutton = (Button)findViewById(R.id.signUpbtn);
        signUpname = (EditText)findViewById(R.id.signup_edit_name);
        signUpid = (EditText)findViewById(R.id.signup_edit_id);
        signUppassword = (EditText)findViewById(R.id.signup_edit_password);

        signUpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //여기서 회원가입에 대한 것이 나와야함.
                //아이디가 존재하면 안됨.
                //가입되면 insert한다.

                if( signUpname.getText().toString().trim().equals("")){
                    Common.makeToast(mcon, "이름을 입력하세요.");
                }
                else if( signUpid.getText().toString().trim().equals("")){
                    Common.makeToast(mcon, "아이디를 입력하세요.");
                }
                else if( signUppassword.getText().toString().trim().equals("")){
                    Common.makeToast(mcon, "비밀번호를 입력하세요.");
                }
                else{
                    if(startsignUp()){
                        Common.makeToast(mcon, "회원가입을 축하합니다.");
                        finish();
                        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                    else{
                        Common.makeToast(mcon, "이미 존재하는 아이디입니다.");
                    }
                }
            }
        });
    }
    public boolean startsignUp(){
        UserDBHelper udbh = new UserDBHelper(this);
        User user = new User();
        user = udbh.selectUserInfo(signUpid.getText().toString());
        if(user == null){//같은 아이디가 없으므로 승인.
            udbh.insert(2,signUpid.getText().toString(),signUppassword.getText().toString(),signUpname.getText().toString(),1000);
            return true;
        }
        else{//같은 아이디가 있으므로 승인x.
            return false;
        }
    }
}
