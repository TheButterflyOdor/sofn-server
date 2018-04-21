package com.sofn.model.asms;

/**
 * 常用查询参数
 * Created by LIB on 2016/11/24.
 */
public class QueryParameter {

    private String token;//用户token

    private String queryYear;//年

    private String queryMonth;//月

    private String queryDay;//日

    private String queryQuarter ;//季度

    private String dateBegin;//开始时间

    private String dateEnd;//结束时间

    private int start;//开始页数

    private int length;//每页长度

    private String querySheng;//省

    private String queryShi;//市

    private String queryXian;//县

    public String getQueryYear() {
        return queryYear;
    }

    public void setQueryYear(String queryYear) {
        this.queryYear = queryYear;
    }

    public String getQueryMonth() {
        return queryMonth;
    }

    public void setQueryMonth(String queryMonth) {
        this.queryMonth = queryMonth;
    }

    public String getQueryDay() {
        return queryDay;
    }

    public void setQueryDay(String queryDay) {
        this.queryDay = queryDay;
    }

    public String getQueryQuarter() {
        return queryQuarter;
    }

    public void setQueryQuarter(String queryQuarter) {
        this.queryQuarter = queryQuarter;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getQuerySheng() {
        return querySheng;
    }

    public void setQuerySheng(String querySheng) {
        this.querySheng = querySheng;
    }

    public String getQueryShi() {
        return queryShi;
    }

    public void setQueryShi(String queryShi) {
        this.queryShi = queryShi;
    }

    public String getQueryXian() {
        return queryXian;
    }

    public void setQueryXian(String queryXian) {
        this.queryXian = queryXian;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
