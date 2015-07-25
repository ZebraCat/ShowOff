package com.example.omri.showoff.Helpers;

import java.util.Date;

/**
 * Created by Asaf on 24-Jul-15.
 */
public class QueryParams {

    private Date date;
    private int pageNum;
    private int queryNum;

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
}
