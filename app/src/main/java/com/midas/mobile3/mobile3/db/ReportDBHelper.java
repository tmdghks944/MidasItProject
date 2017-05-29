package com.midas.mobile3.mobile3.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.midas.mobile3.mobile3.db_model.Report;
import com.midas.mobile3.mobile3.db_model.User;

import java.util.ArrayList;

/**
 * Created by tjssm on 2017-05-27.
 */

public class ReportDBHelper extends SQLiteOpenHelper {
    // The database name
    private static final String DATABASE_NAME = "report.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // Constructor
    public ReportDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold waitlist data
        final String SQL_CREATE_REPORT_TABLE = "CREATE TABLE REPORT (" +
                "report_code INTEGER PRIMARY KEY AUTOINCREMENT," +
                "business_code INTEGER NOT NULL," +
                "report_content VARCHAR(500) NOT NULL," +
                "report_img_cnt INTEGER NOT NULL," +
                "report_img_url_1 VARCHAR(300)," +
                "report_img_url_2 VARCHAR(300)," +
                "report_img_url_3 VARCHAR(300)," +
                "report_img_url_4 VARCHAR(300)," +
                "report_img_url_5 VARCHAR(300)," +
                "FOREIGN KEY (business_code) REFERENCES BUSINESS (business_code) ON DELETE CASCADE" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_REPORT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "REPORT");
        onCreate(sqLiteDatabase);
    }

    //insert
    public void insertReport(int businessCode, String content, ArrayList<String> imgUrlList) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        switch (imgUrlList.size()){
            case 0:
                db.execSQL("INSERT INTO REPORT(business_code, report_content, report_img_cnt) " +
                        "VALUES (" + businessCode + ", '" + content + "', 0);");
                break;
            case 1:
                db.execSQL("INSERT INTO REPORT(business_code, report_content, report_img_cnt, report_img_url_1) " +
                        "VALUES (" + businessCode + ", '" + content + "', 1, '" + imgUrlList.get(0)+ "');");
                break;
            case 2:
                db.execSQL("INSERT INTO REPORT(business_code, report_content, report_img_cnt, report_img_url_1, report_img_url_2) " +
                        "VALUES (" + businessCode + ", '" + content + "', 2, '" + imgUrlList.get(0) + "', '" + imgUrlList.get(1) + "');");
                break;
            case 3:
                db.execSQL("INSERT INTO REPORT(business_code, report_content, report_img_cnt, report_img_url_1, report_img_url_2, report_img_url_3) " +
                        "VALUES (" + businessCode + ", '" + content + "', 3, '" + imgUrlList.get(0) + "', '" + imgUrlList.get(1) + "', '" + imgUrlList.get(2) + "');");
                break;
            case 4:
                db.execSQL("INSERT INTO REPORT(business_code, report_content, report_img_cnt, report_img_url_1, report_img_url_2, report_img_url_3, report_img_url_4) " +
                        "VALUES (" + businessCode + ", '" + content + "', 4, '" + imgUrlList.get(0) + "', '" + imgUrlList.get(1) + "', '" + imgUrlList.get(2) + "', '" + imgUrlList.get(3) + "');");
                break;
            case 5:
                db.execSQL("INSERT INTO REPORT(business_code, report_content, report_img_cnt, report_img_url_1, report_img_url_2, report_img_url_3, report_img_url_4, report_img_url_5) " +
                        "VALUES (" + businessCode + ", '" + content + "', 5, '" + imgUrlList.get(0) + "', '" + imgUrlList.get(1) + "', '" + imgUrlList.get(2) + "', '" + imgUrlList.get(3) + "', '" + imgUrlList.get(4) + "');");
                break;
        }

        db.close();
    }

    //delete = 회원탈퇴?
    public void delete(int reportCode) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM REPORT WHERE report_code = " + reportCode + ";");
        db.close();
    }

    //select
    public ArrayList<Report> selectReport() {
        // 읽기가 가능하게 DB 열기
        ArrayList<Report> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM REPORT ", null);

        if( cursor.getCount() > 0 ){
            result = new ArrayList<Report>();

            Report node = null;

            while (cursor.moveToNext()) {
                node = new Report();

                node.reportCode = cursor.getInt(0);
                node.businessCode = cursor.getInt(1);
                node.reportContent = cursor.getString(2);

                int cnt = cursor.getInt(3);

                String imgUrl = "";

                switch (cnt){
                    case 0:
                        node.reportImgUrlList = null;
                        break;
                    case 1:
                        node.reportImgUrlList = new ArrayList<String>();
                        imgUrl = cursor.getString(4);
                        node.reportImgUrlList.add(imgUrl);
                        break;
                    case 2:
                        node.reportImgUrlList = new ArrayList<String>();
                        imgUrl = cursor.getString(4);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(5);
                        node.reportImgUrlList.add(imgUrl);
                        break;
                    case 3:
                        node.reportImgUrlList = new ArrayList<String>();
                        imgUrl = cursor.getString(4);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(5);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(6);
                        node.reportImgUrlList.add(imgUrl);
                        break;
                    case 4:
                        node.reportImgUrlList = new ArrayList<String>();
                        imgUrl = cursor.getString(4);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(5);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(6);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(7);
                        node.reportImgUrlList.add(imgUrl);
                        break;
                    case 5:
                        node.reportImgUrlList = new ArrayList<String>();
                        imgUrl = cursor.getString(4);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(5);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(6);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(7);
                        node.reportImgUrlList.add(imgUrl);
                        imgUrl = cursor.getString(8);
                        node.reportImgUrlList.add(imgUrl);
                        break;
                }

                result.add(node);
            }
        }
        return result;
    }
}