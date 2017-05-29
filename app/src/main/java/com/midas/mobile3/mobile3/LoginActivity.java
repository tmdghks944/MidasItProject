package com.midas.mobile3.mobile3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midas.mobile3.mobile3.db.BusinessDBHelper;
import com.midas.mobile3.mobile3.db.CompleteDBHelper;
import com.midas.mobile3.mobile3.db.DonationDBHelper;
import com.midas.mobile3.mobile3.db.ReportDBHelper;
import com.midas.mobile3.mobile3.db.RequestDBHelper;
import com.midas.mobile3.mobile3.db.UserDBHelper;
import com.midas.mobile3.mobile3.db.VoluntaryDBHelper;
import com.midas.mobile3.mobile3.db_model.Request;
import com.midas.mobile3.mobile3.db_model.User;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LoginActivity extends AppCompatActivity {

    Context mcon;

    EditText editID,editPassword;
    Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mcon = this;

        setLayout();

        if( Shared.getIsFirst(this) ){
            insertDummyData();
            Shared.setIsFirst(this, false);
        }
    }

    private void insertDummyData(){
        UserDBHelper udbh = new UserDBHelper(this);

        udbh.insert(1, "admin", "1234", "관리자", 9009);


        // 임의 더미데이터 생성 - 나중에 지울것
        VoluntaryDBHelper vdbh = new VoluntaryDBHelper(this);
        String str1="2017-05-20 11:22:33.444";
        String str2="2017-05-21 12:23:33.444";
        String str3="2017-05-29 13:24:33.444";
        String str4="2017-05-30 14:25:33.444";
        Timestamp timestamp1 = null;
        Timestamp timestamp2 = null;
        Timestamp timestamp3 = null;
        Timestamp timestamp4 = null;

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        vdbh.insert("봉사활동1", timestamp1, timestamp2, timestamp3, timestamp4, 100, "연탄나르는 봉사", 0, "");

        str1="2017-05-10 09:22:33.444";
        str2="2017-05-11 10:23:33.444";
        str3="2017-06-29 13:24:33.444";
        str4="2017-07-30 14:25:33.444";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            //look the origin of excption
        }

        vdbh.insert("봉사활동2", timestamp1, timestamp2, timestamp3, timestamp4, 200, "연탄나르는 봉사2", 0, "");

        str1="2017-04-10 09:22:33.444";
        str2="2017-04-11 10:23:33.444";
        str3="2017-06-29 13:24:33.444";
        str4="2017-07-30 14:25:33.444";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        vdbh.insert("봉사활동3", timestamp1, timestamp2, timestamp3, timestamp4, 300, "연탄나르는 봉사3", 0, "");

        str1="2017-08-10 09:22:33.444";
        str2="2017-08-11 10:23:33.444";
        str3="2017-09-29 13:24:33.444";
        str4="2017-09-30 14:25:33.444";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        vdbh.insert("봉사활동5", timestamp1, timestamp2, timestamp3, timestamp4, 900, "달리기봉사2", 0, "");

        str1="2017-02-16 00:00:00.000";
        str2="2017-05-27 23:59:59.000";
        str3="2017-03-04 00:00:00.000";
        str4="2017-06-03 23:59:59.000";

        String volContent="1. 일시 : 매주 토요일 오후 2시부터 5시까지 \n" +
                "2. 장소 : 구세군 다문화센터 \n" +
                "3. 자격 : 이주민 무료급식을 위해 봉사 하실 분 \n" +
                "4. 기타 : 반드시 봉사하실 분만 신청하시고 승인 확인 후에 봉사 가능 \n";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("구세군 다문화센터-이주민 무료급식 봉사", timestamp1, timestamp2, timestamp3, timestamp4, 250, volContent, 0, "http://www.jasunnambi.or.kr/nr/files/2013/12/23/f5f1c83d23859c6885c3b1d3cf788043120349.jpg");

        str1="2017-02-11 00:00:00.000";
        str2="2017-06-30 23:59:59.000";
        str3="2017-03-04 00:00:00.000";
        str4="2017-06-30 23:59:59.000";

        volContent="1.목적 : 큰나무작은도서관에서는 제3세계 어린이 가정을 위한 사랑의 천연비누를 만듭니다. \n" +
                "전문강사님의 지도로 천연비누를 정성껏 2개 만들어 하나는 본인이, 하나는 어려운 제3세계아이들 가정에 기부하여 사랑의 나눔과 베품을 배울 수 있는 시간을 갖게 되는 프로그램입니다. 그리고 천연비누를 만들면서 나눔에 대한 시청각교육을 통해 나눔을 체험할 수 있고 지구촌에 사는 어려운 이웃을 생각하며 배려하는 소중한 시간입니다. \n" +
                "자원봉사자들이 만든 천연비누는 국제기아대책기구를 통하여 제3세계 어려운 어린이 가정에 전달됩니다. \n" +
                "2.시간 : 매주 토요일 오후1시   \n" +
                "3.장소 : 수원시 팔달구 우만동 501-13번지 큰나무작은도서관(수원엘림교회)";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("제3세계 어린이를 위한 천연비누 만들기", timestamp1, timestamp2, timestamp3, timestamp4, 300, volContent, 0, "http://www.okinews.com/news/photo/201308/60517_35603_5722.jpg");


        str1="2017-02-27 00:00:00.000";
        str2="2017-07-30 23:59:59.000";
        str3="2017-03-06 00:00:00.000";
        str4="2017-08-15 23:59:59.000";

        volContent="1. 활동일시: 월요일, 수요일, 금요일중 협의 \n" +
                "2. 활동내용: 초등 저학년 대상 미술관련활동(그리기,수채화) \n" +
                "3. 활동시간: 전화로 문의하여 주세요~ \n" +
                "4. 모집인원: 1-3명이상 \n" +
                "**전화신청만 받습니다.053-353-3017";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("초등 저학년 대상 미술 봉사", timestamp1, timestamp2, timestamp3, timestamp4, 150, volContent, 0, "http://cfile5.uf.tistory.com/image/16792D3F515BB32C157738");


        str1="2017-04-27 00:00:00.000";
        str2="2017-09-02 23:59:59.000";
        str3="2017-05-15 00:00:00.000";
        str4="2017-09-15 23:59:59.000";

        volContent="봉사내용 : 아프리카 말라위 신생아 털모자를 뜨는 봉사\n" +
                "봉사자모집대상 : 중, 고등학생\n" +
                "봉사일시 : 매주 토요일(오후2시-5시)\n" +
                "봉사장소 : 용산구 한강대로98가길 11 (갈월동 7-109) 용산교회2층";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("아프리카 신생아 털모자뜨기 봉사", timestamp1, timestamp2, timestamp3, timestamp4, 400, volContent, 0, "http://cfile5.uf.tistory.com/image/1608C0395084AB5F1BF60F");


        str1="2017-02-27 00:00:00.000";
        str2="2017-06-14 23:59:59.000";
        str3="2017-05-15 00:00:00.000";
        str4="2017-09-30 23:59:59.000";

        volContent="장애인활동지원과 식사보조및 말벗봉사입니다. \n" +
                "시각장애인 화장실도우미와 다른장애분들 식사도우미하시고 말벗하시면 됩니다. \n" +
                "봉사자는 성인분만 가능하며 3개월동안 꾸준히 오셔야합니다. 꼭!꼭!꼭! 꾸준히 오실분만 신청해주세요. \n" +
                "문의= (010-9098-6303) 신청만 하시고 안오시면 안됩니다.";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("장애인 활동 보조 및 식사 보조", timestamp1, timestamp2, timestamp3, timestamp4, 300, volContent, 0, "http://cfile1.uf.tistory.com/image/265D0843577B1E3B1510DA");


        str1="2017-03-15 00:00:00.000";
        str2="2017-06-20 23:59:59.000";
        str3="2017-04-01 00:00:00.000";
        str4="2017-07-15 23:59:59.000";

        volContent="1. 봉사 일시 :2017년4월1월~5월27일 \n" +
                "2. 활동장소: 영통육아종합지원센터 3층 장난감도서관 \n" +
                "3. 봉사내용: 장난감 소독 및 세척 \n" +
                "4. 준비물 : 편안한 복장 \n" +
                "5. 담당자: 이경희(273-3390)";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("영통도서관 내 장난감 소독 및 세척", timestamp1, timestamp2, timestamp3, timestamp4, 100, volContent, 0, "http://cphoto.asiae.co.kr/listimglink/6/2013062710422851792_1.jpg");

        str1="2017-03-25 00:00:00.000";
        str2="2017-05-29 23:59:59.000";
        str3="2017-03-30 00:00:00.000";
        str4="2017-09-01 23:59:59.000";

        volContent="일시: 매월 마지막주 토요일 오전 9시~13시(4시간)\n" +
                "장소: 신풍동 92-2 행궁아해꿈누리 마당 (행궁동주민센터 옆 -한옥건물)\n" +
                "활동내용: 전통놀이터 관리봉사(영유아 가족이 이용고객)-놀잇감 이용안내, 정리, 사용대장관리\n" +
                "필요인원:청소년(고교생이상), 대학생 및 직장인 등 성인 2명";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("행궁아해꿈누리 관리봉사", timestamp1, timestamp2, timestamp3, timestamp4, 270, volContent, 0, "http://www.noriyon.co.kr/data/file/sub03_01/thumb-3552730100_JFz92A8X_11050806_742978235824004_1880866285416581510_n_600x338.jpg");


        str1="2017-03-21 00:00:00.000";
        str2="2017-09-17 23:59:59.000";
        str3="2017-04-21 00:00:00.000";
        str4="2017-10-11 23:59:59.000";

        volContent="시간 및 장소 : 10시 박물관 1층 사무실\n" +
                "지참물 : 본인 확인을 위한 신분증(학생증) 지참\n" +
                "복 장 : 활동하기 편안하고 단정한 복장 (추위를 대비하여 따뜻하게 입고오세요)\n" +
                "집결장소에 모여 본인 출석 확인 (1365 가입자 아이디 기입 등)";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception

        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("유기농페스티벌 자원봉사자 모집", timestamp1, timestamp2, timestamp3, timestamp4, 120, volContent, 0, "http://4.bp.blogspot.com/-2bVuhXG0Jhg/VEcpbKM6iRI/AAAAAAAAORA/aLWUbSth5Ls/s1600/%EB%B8%94%EB%9F%AC%EA%B7%B8%2B%EB%82%A8%EC%96%91%EC%A3%BC%2B%EC%8A%AC%EB%A1%9C%EC%9A%B0%EB%9D%BC%EC%9D%B4%ED%94%84%EB%AC%BC%EC%9D%98%EC%A0%95%EC%9B%90%2B%EB%84%A4%EC%9D%B4%EB%B2%84%2B7205.JPG");

        str1="2017-04-03 00:00:00.000";
        str2="2017-07-21 23:59:59.000";
        str3="2017-04-21 00:00:00.000";
        str4="2017-09-24 23:59:59.000";

        volContent="토일 12시부터 5시까지 체험 및 행사 보조 자봉을 구합니다. (봉사시간 5시간 인정)\n" +
                "고등학생 이상 부터 지원해주세요\n" +
                "12시부터 시작이므로 미리 식사는 하고 오세요~\n" +
                "11월까지 계속 행사를 할 예정이므로 꾸준히 신청해주세요\n" +
                "문의사항은 287-6305 로 연락주세요";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("전주전통술박물관 활동보조", timestamp1, timestamp2, timestamp3, timestamp4, 270, volContent, 0, "http://cfile227.uf.daum.net/image/2377FE3C53A163B203A4D3");

        str1="2017-04-29 00:00:00.000";
        str2="2017-05-30 23:59:59.000";
        str3="2017-05-01 00:00:00.000";
        str4="2017-07-01 23:59:59.000";

        volContent="1. 활동일시 : 2017년 5월 13일~5월 27일 매주 토요일 15:00~18:00 (3시간 활동)\n" +
                "2. 활동장소 : 시민공원 안용복 세미나실\n" +
                "3. 활동내용 : 양성평등 및 좋은부모되기 교육 및 홍보 활동\n" +
                "4. 문의사항 : 한미상담심리학회 장수진 051-246-2781";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str1);
            timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str2);
            timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str3);
            timestamp3 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str4);
            timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }


        vdbh.insert("좋은부모되기 교육 및 홍보 활동", timestamp1, timestamp2, timestamp3, timestamp4, 220, volContent, 0, "http://news20.busan.com/content/image/2017/04/02/20170402000089_0.jpg");


        RequestDBHelper rdbh = new RequestDBHelper(this);
        rdbh.insert(1, 9, timestamp1);
        rdbh.insert(1, 7, timestamp2);
        rdbh.insert(1, 6, timestamp3);


        CompleteDBHelper cdbh = new CompleteDBHelper(this);
        String str="2017-05-01 00:00:00.000";
        Timestamp timestamp = null;

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        cdbh.insert(timestamp, Common.userCode, 8);
        cdbh.insert(Common.userCode, 5);

        str="2017-05-05 00:00:00.000";
        timestamp = null;

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
        }

        DonationDBHelper ddbh = new DonationDBHelper(this);
        ddbh.insert(Common.userCode, 1, 100);
        ddbh.insert(timestamp, Common.userCode, 3, 200);


        ArrayList<String> imgUrlList = new ArrayList<String>();
        imgUrlList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0pdQnAZ86GqM81IGaj9ODPYR7bgVG4_kgocVhXiIVrD5JtoTKGA");
        imgUrlList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJAqvBcgMc3hOpqI4FoxzK68Sh34gkdemoHrsrf7hMQoFaHD_Nhg");


        ReportDBHelper rpdbh = new ReportDBHelper(this);
        rpdbh.insertReport(3, "우앙 신나는 연탄나르기", imgUrlList);

        BusinessDBHelper bdbh = new BusinessDBHelper(this);
        String bName="여러분들 덕에 내가 삽니더";
        String bContent="자원봉사자들이 밑반찬을 직접 만들고 배달해 줌으로써 봉화군 독거 어르신들이 \n" +
                "건강을 유지하실 뿐 아니라 지역사회 속에서 유대관계를 경험하고 유지하면서 심리적 안정을 누리게 됩니다.";
        String bUrl="http://happybean.phinf.naver.net/20161130_119/hlog_h07084_1480475445557C3JE6_JPEG/%C5%EB%C0%CC%B9%CC%C1%F6jpg?type=w720";
        bdbh.insert(bName,bContent,1000000,0,bUrl);

        bName="소녀가장 할머니의 치과진료를 지원해주세요";
        bContent="홀로 남은 소녀가장 엄가영 어르신(가명)에게 치과진료를 지원합니다.\n" +
                "홀로 남은 소녀가장 엄가영 어르신(가명)에게 12개월 식료품을 지원합니다." +
                "엄가영 어르신(가명)의 치과진료를 통해 영양 불균형을 해소하여 건강상태 회복지원";
        bUrl="http://happybean.phinf.naver.net/20170214_143/hlog_e03258_14870517017694L0np_JPEG/photo_1486538470jpg?type=w720";
        bdbh.insert(bName,bContent,5000000,0,bUrl);

        bName="소리없는 세상을 살아가는 어르신";
        bContent="인공달팽이관 수술 지원으로 듣지 못해 사회적으로 소외되고 있는 어르신에게 소리를 찾아주어 \n" +
                "사회와 소통 할 수 있는 발판을 마련해주고, 소통의 부재로 인한 고독사를 방지합니다.";
        bUrl="http://happybean.phinf.naver.net/20170207_148/hlog_s02634_1486429594460WXuEW_JPEG/%C7%D2%BE%C6%B9%F6%C1%F6%C4%BF%B9%F6jpg?type=w720";
        bdbh.insert(bName,bContent,6000000,0,bUrl);

        bName="하루하루 살아가기 위해 폐휴지를 줍는 어르신들";
        bContent="1. 생계형 폐휴지 수거 어르신들의 교통사고률 50% 감소\n" +
                "2. 생계형 폐휴지 수거 어르신들의 삶의 질 향상\n" +
                "3. 생계형 폐휴지 수거 어르신들의 생계물품지원 12회 이상\n" +
                "4. 생계형 폐휴지 수거 어르신들의 생계 증대";
        bUrl="http://happybean.phinf.naver.net/20160801_61/hlog_m06140_14700480282429xNPl_JPEG/IMG_7794.JPG?type=w720";
        bdbh.insert(bName,bContent,9000000,0,bUrl);

        bName="어르신들의 따뜻한 한 끼를 지켜주세요";
        bContent="홀로 사는 어르신의 건강증진을 도모하고자 광주 서구 지역에 거주중인 독거어르신 댁에 주1회 밑반찬을 배달 드리고자 합니다.\n";
        bUrl="http://happybean.phinf.naver.net/20170207_101/hlog_s03859_1486424923441iU6GA_JPEG/%BB%E7%C1%F8_3.JPG?type=w720";
        bdbh.insert(bName,bContent,4000000,0,bUrl);
    }

    private void setLayout(){
        editID = (EditText)findViewById(R.id.login_edit_id);
        editPassword = (EditText)findViewById(R.id.login_edit_password);

        btnLogin = (Button)findViewById(R.id.login_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( editID.getText().toString().trim().equals("") ){
                    Common.makeToast(mcon, "아이디를 입력하세요.");
                }
                else if( editPassword.getText().toString().trim().equals("")){
                    Common.makeToast(mcon, "비밀번호를 입력하세요.");
                }
                else{
                    if(isChecked(editID.getText().toString()) == true) {//로그인 성공.
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);

                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                }


                /*Intent i = new Intent(LoginActivity.this, MainActivity.class);

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);*/
            }
        });

        btnSignup = (Button)findViewById(R.id.login_btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public Boolean isChecked(String id){
        UserDBHelper udbh = new UserDBHelper(this);
        User user = new User();
        user = udbh.selectUserInfo(id);
        if(user==null){
            Common.makeToast(this, "존재하지 않는 아이디입니다.");
            return false;
        }
        else{
            if(user.userPW.equals(editPassword.getText().toString())){
                if( user.userSort == 1 ){
                    Shared.setIsAdmin(mcon, true);
                }
                else{
                    Shared.setIsAdmin(mcon, false);
                }

                Common.userCode = user.userCode;
                return true;
            }
            else{
                Common.makeToast(this, "비밀번호가 틀렸습니다.");
                return false;
            }
        }
    }
}