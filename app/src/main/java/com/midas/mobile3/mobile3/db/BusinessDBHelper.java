package com.midas.mobile3.mobile3.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.midas.mobile3.mobile3.db_model.Business;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * Created by tjssm on 2017-05-27.
 */

public class BusinessDBHelper extends SQLiteOpenHelper {
    // The database name
    private static final String DATABASE_NAME = "business.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // Constructor
    public BusinessDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold waitlist data
        final String SQL_CREATE_BUSINESS_TABLE = "CREATE TABLE BUSINESS (" +
                "business_code INTEGER PRIMARY KEY AUTOINCREMENT," +
                "business_name VARCHAR(100) NOT NULL," +
                "business_content VARCHAR(3000) NOT NULL," +
                "business_goal_point INTEGER NOT NULL," +
                "business_cur_point INTEGER NOT NULL," +
                "business_img_url VARCHAR(500) NOT NULL" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_BUSINESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "BUSINESS");
        onCreate(sqLiteDatabase);
    }

    //insert
    public void insert(String name, String content, int goalPoint, int curPoint, String imgUrl) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        //timestamp가 ''가 들어가나 안들어가나 모르겠다!.

        db.execSQL("INSERT INTO BUSINESS (business_name,  business_content, business_goal_point, business_cur_point, business_img_url) " +
                "VALUES ('" + name + "', '" + content + "', " + goalPoint +", " + curPoint + ", '" + imgUrl + "');");
        db.close();
    }

    //delete
    public void delete(int code) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM BUSINESS WHERE business_code = " + code + ";");
        db.close();
    }

    //select
    public ArrayList<Business> selectBusinessIng() {
        // 읽기가 가능하게 DB 열기
        ArrayList<Business> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM BUSINESS " +
                "WHERE business_goal_point > business_cur_point " +
                "ORDER BY business_goal_point", null);

        if( cursor.getCount() > 0 ){
            result = new ArrayList<Business>();

            Business node = null;

            while (cursor.moveToNext()) {
                node = new Business();

                node.businessCode = cursor.getInt(0);
                node.businessName = cursor.getString(1);
                node.businessContent= cursor.getString(2);
                node.businessGoalPoint = cursor.getInt(3);
                node.businessCurPoint= cursor.getInt(4);
                node.businessImgUrl = cursor.getString(5);

                result.add(node);
            }
        }

        return result;
    }

    public Business selectBusiness(int businessCode) {
        // 읽기가 가능하게 DB 열기
        Business result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM BUSINESS " +
                "WHERE business_code = " + businessCode, null);

        if( cursor.getCount() > 0 ){
            result = new Business();

            while (cursor.moveToNext()) {
                result.businessCode = cursor.getInt(0);
                result.businessName = cursor.getString(1);
                result.businessContent= cursor.getString(2);
                result.businessGoalPoint = cursor.getInt(3);
                result.businessCurPoint= cursor.getInt(4);
                result.businessImgUrl = cursor.getString(5);
            }
        }

        return result;
    }

    //update : 추가필요, 관리자가 봉사활동 내용 바꿨을때
    public void updateBusinessCurPoint(int code, int point) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE BUSINESS SET business_cur_point = " + point + " WHERE business_code = " + code + ";");
        db.close();
    }
}
