package com.midas.mobile3.mobile3.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.midas.mobile3.mobile3.db_model.User;

/**
 * Created by tjssm on 2017-05-27.
 */

public class UserDBHelper extends SQLiteOpenHelper {
    // The database name
    private static final String DATABASE_NAME = "user.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // Constructor
    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold waitlist data
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE USER (" +
                "user_code INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_sort INTEGER NOT NULL," +
                "user_id VARCHAR(50) NOT NULL UNIQUE," +
                "user_pw VARCHAR(100) NOT NULL," +
                "user_name VARCHAR(100) NOT NULL," +
                "user_cur_point INTEGER NOT NULL" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "USER");
        onCreate(sqLiteDatabase);
    }

    //insert = 회원가입
    public void insert(int sort, String id, String pw, String name, int point) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO USER(user_sort, user_id, user_pw, user_name, user_cur_point) " +
                "VALUES (" + sort + ", '" + id + "', '" + pw +"', '" + name + "', " + point + ");");
        db.close();
    }

    //delete = 회원탈퇴?
    public void delete(int code) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM USER WHERE user_code = " + code + ";");
        db.close();
    }

    //select
    public User selectUserInfo(String id) {
        // 읽기가 가능하게 DB 열기
        User result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM USER " +
                "WHERE user_id = '" + id + "'", null);

        if( cursor.getCount() > 0 ){
            result = new User();

            while (cursor.moveToNext()) {
                result.userCode = cursor.getInt(0);
                result.userSort = cursor.getInt(1);
                result.userId = cursor.getString(2);
                result.userPW = cursor.getString(3);
                result.userName = cursor.getString(4);
                result.userCurPoint = cursor.getInt(5);
            }
        }
        return result;
    }

    public User selectUserInfo(int userCode) {
        // 읽기가 가능하게 DB 열기
        User result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM USER " +
                "WHERE user_code = '" + userCode + "'", null);

        if( cursor.getCount() > 0 ){
            result = new User();

            while (cursor.moveToNext()) {
                result.userCode = cursor.getInt(0);
                result.userSort = cursor.getInt(1);
                result.userId = cursor.getString(2);
                result.userPW = cursor.getString(3);
                result.userName = cursor.getString(4);
                result.userCurPoint = cursor.getInt(5);
            }
        }
        return result;
    }

    //update
    public void updateUserCurPoint(int code, int point) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE USER SET user_cur_point = " + point + " WHERE user_code = " + code + ";");
        db.close();
    }
}
