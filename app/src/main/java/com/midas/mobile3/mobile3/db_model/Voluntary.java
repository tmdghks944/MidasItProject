package com.midas.mobile3.mobile3.db_model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by tjssm on 2017-05-27.
 */

public class Voluntary implements Serializable{
    public int voluntaryCode;
    public String voluntaryTitle;
    public Timestamp voluntaryReqStartDate;
    public Timestamp voluntaryReqEndDate;
    public Timestamp voluntaryExcStartDate;
    public Timestamp voluntaryExcEndDate;
    public int voluntaryPoint;
    public String voluntaryContent;
    public int voluntarySort;
    public String voluntaryImg;

    @Override
    public String toString() {
        String str = voluntaryCode + " : " + voluntaryTitle + " : " + voluntaryPoint + " : " + voluntaryExcEndDate;
        return str;
    }
}
