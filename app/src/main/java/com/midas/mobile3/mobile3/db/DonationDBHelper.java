package com.midas.mobile3.mobile3.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.midas.mobile3.mobile3.db_model.Donation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tjssm on 2017-05-27.
 */

public class DonationDBHelper extends SQLiteOpenHelper {
    // The database name
    private static final String DATABASE_NAME = "donation.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // Constructor
    public DonationDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold waitlist data
        final String SQL_CREATE_DONATION_TABLE = "CREATE TABLE DONATION (" +
                "donation_code INTEGER PRIMARY KEY AUTOINCREMENT," +
                "donation_date TIMESTAMP NOT NULL," +
                "user_code INTEGER NOT NULL," +
                "business_code INTEGER NOT NULL," +
                "donation_point INTEGER NOT NULL," +
                "FOREIGN KEY (user_code) REFERENCES USER (user_code) ON DELETE CASCADE," +
                "FOREIGN KEY (business_code) REFERENCES BUSINESS (business_code) ON DELETE CASCADE" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_DONATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "DONATION");
        onCreate(sqLiteDatabase);
    }

    //insert = 관리자가 봉사활동 했다고 승인
    public void insert(int userCode, int businessCode, int point) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();

        // 현재시간 가져오기
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO DONATION(donation_date, user_code, business_code, donation_point) " +
                "VALUES ('" + ts.toString() + "', " + userCode + ", " + businessCode + ", " + point + ");");
        db.close();
    }

    public void insert(Timestamp ts, int userCode, int businessCode, int point) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();

        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO DONATION(donation_date, user_code, business_code, donation_point) " +
                "VALUES ('" + ts.toString() + "', " + userCode + ", " + businessCode + ", " + point + ");");
        db.close();
    }

    //delete = 쓸일 없을듯
    public void delete(int donationCode) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM DONATION WHERE donation_code = " + donationCode + ";");
        db.close();
    }

    //select = 사용자가 기부한 리스트
    public ArrayList<Donation> selectDonation(int userCode) {
        // 읽기가 가능하게 DB 열기
        ArrayList<Donation> result = null;
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM DONATION " +
                "WHERE user_code = " + userCode, null);

        if (cursor.getCount() > 0) {//
            result = new ArrayList<Donation>();

            Donation node = null;

            while (cursor.moveToNext()) {
                node = new Donation();

                node.donationCode = cursor.getInt(0);
                node.donationDate = Timestamp.valueOf(cursor.getString(1));
                node.userCode = cursor.getInt(2);
                node.businessCode = cursor.getInt(3);
                node.donationPoint = cursor.getInt(4);

                result.add(node);
            }
        }

        return result;
    }
}
