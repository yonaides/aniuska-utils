/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.utils.date;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author hectorvent@gmail,com
 */
public final class DateRange implements Serializable {

    private Date firstDate;
    private Date endDate;

    public DateRange() {
        nowRange();
    }

    public void nowRange() {
        firstDate = new Date();
        endDate = new Date();
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public DateRange setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public DateRange setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    @Override
    public String toString() {
        return "DateRange{" + "firstDate=" + DateUtils.dateTime2String(firstDate) + ", endDate=" + DateUtils.dateTime2String(endDate) + '}';
    }

}
