package com.midas.mobile3.mobile3.db_model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by tjssm on 2017-05-27.
 */

public class PointThing implements Serializable {
    public int code;
    public int sort; // 1 : Complete, 2 : Donation
    public Timestamp date;
    public int point;
}
