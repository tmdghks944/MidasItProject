package com.midas.mobile3.mobile3.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.midas.mobile3.mobile3.db_model.Voluntary;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * Created by tjssm on 2017-05-27.
 */

public class VoluntaryDBHelper extends SQLiteOpenHelper {
    // The database name
    private static final String DATABASE_NAME = "voluntary.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // Constructor
    public VoluntaryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold waitlist data
        final String SQL_CREATE_VOLUNTARY_TABLE = "CREATE TABLE VOLUNTARY (" +
                "vs_code INTEGER PRIMARY KEY AUTOINCREMENT," +
                "vs_title VARCHAR(100) NOT NULL," +
                "vs_req_start_date TIMESTAMP NOT NULL," +
                "vs_req_end_date TIMESTAMP NOT NULL," +
                "vs_exc_start_date TIMESTAMP NOT NULL," +
                "vs_exc_end_date TIMESTAMP NOT NULL," +
                "vs_point INTEGER NOT NULL," +
                "vs_content VARCHAR(3000) NOT NULL," +
                "vs_sort INTEGER NOT NULL," +
                "vs_img_url VARCHAR(500) NOT NULL" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_VOLUNTARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "VOLUNTARY");
        onCreate(sqLiteDatabase);
    }

    //insert = 회원가입
    public void insert(String title, Timestamp req_start_date, Timestamp req_end_date, Timestamp exc_start_date,
                       Timestamp exc_end_date, int point, String content ,int sort, String imgUrl) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        //timestamp가 ''가 들어가나 안들어가나 모르겠다!.

        db.execSQL("INSERT INTO VOLUNTARY(vs_title, vs_req_start_date, vs_req_end_date, vs_exc_start_date, vs_exc_end_date, vs_point, vs_content, vs_sort, vs_img_url) " +
                "VALUES ('" + title + "', '" + req_start_date + "', '" + req_end_date +"', '" + exc_start_date + "', '" + exc_end_date + "', "
                + point + ", '" + content + "', " + sort + ", '" + imgUrl + "');");
        db.close();
    }

    //delete = 회원탈퇴?
    public void delete(int code) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM VOLUNTARY WHERE vs_code = " + code + ";");
        db.close();
    }

    //select
    public Voluntary selectVoluntaryInfo(String title) {
        // 읽기가 가능하게 DB 열기
        Voluntary result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM VOLUNTARY " +
                "WHERE vs_title = " + title, null);

        if( cursor.getCount() > 0 ){
            result = new Voluntary();

            while (cursor.moveToNext()) {
                result.voluntaryCode = cursor.getInt(0);
                result.voluntaryTitle = cursor.getString(1);
                result.voluntaryReqStartDate = Timestamp.valueOf(cursor.getString(2));
                result.voluntaryReqEndDate = Timestamp.valueOf(cursor.getString(3));
                result.voluntaryExcStartDate = Timestamp.valueOf(cursor.getString(4));
                result.voluntaryExcEndDate = Timestamp.valueOf(cursor.getString(5));
                result.voluntaryPoint= cursor.getInt(6);
                result.voluntaryContent = cursor.getString(7);
                result.voluntarySort = cursor.getInt(8);
                result.voluntaryImg= cursor.getString(9);
            }
        }

        return result;
    }

    public Voluntary selectVoluntaryInfoId(String code) {
        // 읽기가 가능하게 DB 열기
        Voluntary result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM VOLUNTARY " +
                "WHERE vs_code = " + code, null);

        if( cursor.getCount() > 0 ){
            result = new Voluntary();

            while (cursor.moveToNext()) {
                result.voluntaryCode = cursor.getInt(0);
                result.voluntaryTitle = cursor.getString(1);
                result.voluntaryReqStartDate = Timestamp.valueOf(cursor.getString(2));
                result.voluntaryReqEndDate = Timestamp.valueOf(cursor.getString(3));
                result.voluntaryExcStartDate = Timestamp.valueOf(cursor.getString(4));
                result.voluntaryExcEndDate = Timestamp.valueOf(cursor.getString(5));
                result.voluntaryPoint= cursor.getInt(6);
                result.voluntaryContent = cursor.getString(7);
                result.voluntarySort = cursor.getInt(8);
                result.voluntaryImg= cursor.getString(9);
            }
        }

        return result;
    }

    public Voluntary selectVoluntaryInfo(int vsCode) {
        // 읽기가 가능하게 DB 열기
        Voluntary result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM VOLUNTARY " +
                "WHERE vs_code = " + vsCode, null);

        if( cursor.getCount() > 0 ){
            result = new Voluntary();

            while (cursor.moveToNext()) {
                result.voluntaryCode = cursor.getInt(0);
                result.voluntaryTitle = cursor.getString(1);
                result.voluntaryReqStartDate = Timestamp.valueOf(cursor.getString(2));
                result.voluntaryReqEndDate = Timestamp.valueOf(cursor.getString(3));
                result.voluntaryExcStartDate = Timestamp.valueOf(cursor.getString(4));
                result.voluntaryExcEndDate = Timestamp.valueOf(cursor.getString(5));
                result.voluntaryPoint= cursor.getInt(6);
                result.voluntaryContent = cursor.getString(7);
                result.voluntarySort = cursor.getInt(8);
                result.voluntaryImg = cursor.getString(9);
            }
        }

        return result;
    }

    public ArrayList<Voluntary> selectVoluntaryInfoIng() {
        // 읽기가 가능하게 DB 열기
        ArrayList<Voluntary> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM VOLUNTARY " +
                "WHERE vs_req_end_date >= CURRENT_TIMESTAMP " +
                "ORDER BY vs_req_end_date", null);

        if( cursor.getCount() > 0 ){
            result = new ArrayList<Voluntary>();

            Voluntary node = null;

            while (cursor.moveToNext()) {
                node = new Voluntary();

                node.voluntaryCode = cursor.getInt(0);
                node.voluntaryTitle = cursor.getString(1);
                node.voluntaryReqStartDate = Timestamp.valueOf(cursor.getString(2));
                node.voluntaryReqEndDate = Timestamp.valueOf(cursor.getString(3));
                node.voluntaryExcStartDate = Timestamp.valueOf(cursor.getString(4));
                node.voluntaryExcEndDate = Timestamp.valueOf(cursor.getString(5));
                node.voluntaryPoint= cursor.getInt(6);
                node.voluntaryContent = cursor.getString(7);
                node.voluntarySort = cursor.getInt(8);
                node.voluntaryImg = cursor.getString(9);

                result.add(node);
            }
        }

        return result;
    }

    //update : 추가필요, 관리자가 봉사활동 내용 바꿨을때
    public void updateVoluntaryCurPoint(int code, int point) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE VOLUNTARY SET vs_point = " + point + " WHERE vs_code = " + code + ";");
        db.close();
    }
}
