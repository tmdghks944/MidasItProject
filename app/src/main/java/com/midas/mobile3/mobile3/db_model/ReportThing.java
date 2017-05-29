package com.midas.mobile3.mobile3.db_model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tjssm on 2017-05-28.
 */

public class ReportThing  implements Serializable {
    public int reportCode;
    public String reportContent;
    public ArrayList<String> reportImgUrlList;
    public Business business;
}
