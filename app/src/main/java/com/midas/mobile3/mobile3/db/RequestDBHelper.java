package com.midas.mobile3.mobile3.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.midas.mobile3.mobile3.db_model.Request;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tjssm on 2017-05-27.
 */

public class RequestDBHelper extends SQLiteOpenHelper {
    // The database name
    private static final String DATABASE_NAME = "request.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // Constructor
    public RequestDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold waitlist data
        final String SQL_CREATE_REQUEST_TABLE = "CREATE TABLE REQUEST (" +
                "request_code INTEGER PRIMARY KEY AUTOINCREMENT," +
                "request_sort INTEGER NOT NULL," +
                "request_date TIMESTAMP NOT NULL," +
                "user_code INTEGER NOT NULL," +
                "vs_code INTEGER NOT NULL," +
                "FOREIGN KEY (user_code) REFERENCES USER (user_code) ON DELETE CASCADE," +
                "FOREIGN KEY (vs_code) REFERENCES VOLUNTARY_SERVICE (vs_code) ON DELETE CASCADE" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_REQUEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "REQUEST");
        onCreate(sqLiteDatabase);
    }

    //insert = user가 봉사활동 신청
    public void insert(int userCode, int vsCode, Timestamp ts) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();

        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO REQUEST(request_sort, request_date, user_code, vs_code) " +
                "VALUES (0, '" + ts.toString() + "', " + userCode + ", " + vsCode + ");");
        db.close();
    }

    //delete = 관리자가 미승인 or user가 신청취소
    public void delete(int requestCode) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM REQUEST WHERE request_code = " + requestCode + ";");
        db.close();
    }

    public void delete(int userCode, int voluntaryCode) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM REQUEST WHERE user_code = " + userCode + " AND vs_code = " + voluntaryCode + ";");
        db.close();
    }

    //select = 사용자가 봉사활동 신청한 리스트 반환
    public ArrayList<Request> selectCompleteRequest() {
        // 읽기가 가능하게 DB 열기
        ArrayList<Request> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM REQUEST " +
                "WHERE request_date <= CURRENT_TIMESTAMP", null);

        if (cursor.getCount() > 0) {
            result = new ArrayList<Request>();

            Request node = null;

            while (cursor.moveToNext()) {
                node = new Request();

                node.requestCode = cursor.getInt(0);
                node.requestSort = cursor.getInt(1);
                node.requestDate = Timestamp.valueOf(cursor.getString(2));
                node.userCode = cursor.getInt(3);
                node.vsCode = cursor.getInt(4);

                result.add(node);
            }
        }

        return result;
    }

    public ArrayList<Request> selectRequest(int userCode) {
        // 읽기가 가능하게 DB 열기
        ArrayList<Request> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM REQUEST " +
                "WHERE user_code = " + userCode, null);

        if (cursor.getCount() > 0) {
            result = new ArrayList<Request>();

            Request node = null;

            while (cursor.moveToNext()) {
                node = new Request();

                node.requestCode = cursor.getInt(0);
                node.requestSort = cursor.getInt(1);
                node.requestDate = Timestamp.valueOf(cursor.getString(2));
                node.userCode = cursor.getInt(3);
                node.vsCode = cursor.getInt(4);

                result.add(node);
            }
        }

        return result;
    }

    public ArrayList<Request> selectRequest(int userCode, int voluntaryCode) {
        // 읽기가 가능하게 DB 열기
        ArrayList<Request> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM REQUEST " +
                "WHERE user_code = " + userCode + " AND vs_code = " + voluntaryCode, null);


        if (cursor.getCount() > 0) {
            result = new ArrayList<Request>();

            Request node = null;

            while (cursor.moveToNext()) {
                node = new Request();

                node.requestCode = cursor.getInt(0);
                node.requestSort = cursor.getInt(1);
                node.requestDate = Timestamp.valueOf(cursor.getString(2));
                node.userCode = cursor.getInt(3);
                node.vsCode = cursor.getInt(4);

                result.add(node);
            }
        }

        return result;
    }

    //update = 관리자가 승인시 sort값 변환
    public void updateUserCurPoint(int requestCode, int sort) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE REQUEST SET request_sort = " + sort + " WHERE request_code = " + requestCode + ";");
        db.close();
    }

    public void updateRequestDate(int requestCode) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE REQUEST SET request_date = CURRENT_TIMESTAMP WHERE request_code = " + requestCode + ";");
        db.close();
    }
}
