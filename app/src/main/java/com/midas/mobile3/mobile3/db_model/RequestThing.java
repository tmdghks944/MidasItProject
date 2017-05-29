package com.midas.mobile3.mobile3.db_model;

import com.midas.mobile3.mobile3.db_model.User;
import com.midas.mobile3.mobile3.db_model.Voluntary;

import java.io.Serializable;

/**
 * Created by tjssm on 2017-05-28.
 */

public class RequestThing implements Serializable {
    public int requestCode;
    public User user;
    public Voluntary voluntary;
}
