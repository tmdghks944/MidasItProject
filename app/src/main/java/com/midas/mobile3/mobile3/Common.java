package com.midas.mobile3.mobile3;

import android.content.Context;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by myRoom on 2017-05-27.
 */

public class Common {

    public static int userCode = 1;

    public static String dateToString(Timestamp ts){
        String time = new SimpleDateFormat("yyyy-MM-dd").format(ts);
        return time;
    }

    public static void makeToast(Context con, String str) {
        Toast.makeText(con, str, Toast.LENGTH_SHORT).show();
    }
}
