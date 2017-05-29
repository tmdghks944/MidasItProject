package com.midas.mobile3.mobile3.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.midas.mobile3.mobile3.db_model.Complete;

import java.util.ArrayList;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tjssm on 2017-05-27.
 */

public class CompleteDBHelper extends SQLiteOpenHelper {
    // The database name
    private static final String DATABASE_NAME = "complete.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // Constructor
    public CompleteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold waitlist data
        final String SQL_CREATE_COMPLETE_TABLE = "CREATE TABLE COMPLETE (" +
                "complete_code INTEGER PRIMARY KEY AUTOINCREMENT," +
                "complete_date TIMESTAMP NOT NULL," +
                "user_code INTEGER NOT NULL," +
                "vs_code INTEGER NOT NULL," +
                "FOREIGN KEY (user_code) REFERENCES USER (user_code) ON DELETE CASCADE," +
                "FOREIGN KEY (vs_code) REFERENCES VOLUNTARY (vs_code) ON DELETE CASCADE" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_COMPLETE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "COMPLETE");
        onCreate(sqLiteDatabase);
    }

    //insert = 관리자가 봉사활동 했다고 승인
    public void insert(int userCode, int vsCode) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();

        //현재시간 가져오기
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO COMPLETE(complete_date, user_code, vs_code) " +
                "VALUES ('" + ts.toString() + "', " + userCode + ", " + vsCode + ");");
        db.close();
    }

    public void insert(Timestamp timestamp, int userCode, int vsCode) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();

        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO COMPLETE(complete_date, user_code, vs_code) " +
                "VALUES ('" + timestamp.toString() + "', " + userCode + ", " + vsCode + ");");
        db.close();
    }

    //delete = 쓸일 없을듯
    public void delete(int completeCode) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM COMPLETE WHERE complete_code = " + completeCode + ";");
        db.close();
    }

    //select = 사용자가 봉사활동 완료한 리스트 반환
    public ArrayList<Complete> selectComplete(int userCode) {
        // 읽기가 가능하게 DB 열기
        ArrayList<Complete> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM COMPLETE " +
                "WHERE user_code = " + userCode, null);

        if (cursor.getCount() > 0) {
            result = new ArrayList<Complete>();

            Complete node = null;

            while (cursor.moveToNext()) {
                node = new Complete();

                node.completeCode = cursor.getInt(0);
                node.completeDate = Timestamp.valueOf(cursor.getString(1));
                node.userCode = cursor.getInt(2);
                node.vsCode = cursor.getInt(3);

                result.add(node);
            }
        }

        return result;
    }

    public ArrayList<Complete> selectComplete(int userCode, int vsCode) {
        // 읽기가 가능하게 DB 열기
        ArrayList<Complete> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM COMPLETE " +
                "WHERE user_code = " + userCode + " AND vs_code = " + vsCode, null);

        if (cursor.getCount() > 0) {
            result = new ArrayList<Complete>();

            Complete node = null;

            while (cursor.moveToNext()) {
                node = new Complete();

                node.completeCode = cursor.getInt(0);
                node.completeDate = Timestamp.valueOf(cursor.getString(1));
                node.userCode = cursor.getInt(2);
                node.vsCode = cursor.getInt(3);

                result.add(node);
            }
        }

        return result;
    }
}
