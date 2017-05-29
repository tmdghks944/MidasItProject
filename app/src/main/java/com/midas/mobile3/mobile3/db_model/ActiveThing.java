package com.midas.mobile3.mobile3.db_model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by tjssm on 2017-05-27.
 */

public class ActiveThing implements Serializable{
    public int code;
    public int sort; // 1 : Request(요청상태), 2 : Request(승인상태), 3 : Complete
    public Timestamp date;
    public Voluntary voluntary;
}
