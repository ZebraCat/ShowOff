package com.example.omri.showoff.Helpers;

import java.util.Date;

/**
 * Created by Asaf on 24-Jul-15.
 */
public class QueryParams {

    private Date date;
    private int pageNum;
    private int queryNum;
    private String objectId;
    // this is to find the amount of which to increase/decrease the like counter by
    private int amount;

    public void setDate(Date date){
        this.date = date;
    }

    public void setPageNum(int pageNum){
        this.pageNum = pageNum;
    }

    public Date getDate(){
        return this.date;
    }

    public int getPageNum(){
        return this.pageNum;
    }

    public int getQueryNum(){
        return this.queryNum;
    }

    public void setQueryNum(int queryNum){
        this.queryNum = queryNum;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
