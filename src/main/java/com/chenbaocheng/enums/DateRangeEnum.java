package com.chenbaocheng.enums;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Created by CBC on 2015-04-18 23:38.
 */
public enum DateRangeEnum {

    DAY(1), WEEK(2), MONTH(3), YEAR(4);

    private int type;

    DateRangeEnum(int type) {
        this.type = type;
    }

    public int value() {
        return type;
    }

    public Date getStartDate() {
        LocalDate localDate = LocalDate.now();
        switch (type) {
            case 1:
                break;
            case 2:
                localDate = localDate.with(DayOfWeek.MONDAY);
                break;
            case 3:
                localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
                break;
            case 4:
                localDate = localDate.with(TemporalAdjusters.firstDayOfYear());
                break;
        }

        return Date.from(localDate.minusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getEndDate() {
        LocalDate localDate = LocalDate.now();
        switch (type) {
            case 1:
                break;
            case 2:
                localDate = localDate.with(DayOfWeek.SUNDAY);
                break;
            case 3:
                localDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
                break;
            case 4:
                localDate = localDate.with(TemporalAdjusters.lastDayOfYear());
                break;
        }

        return Date.from(localDate.plusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

//    public static void main(String[] args) {
//        System.out.println(DateRangeEnum.DAY.getStartDate());
//        System.out.println(DateRangeEnum.DAY.getEndDate());
//        System.out.println("----");
//        System.out.println(DateRangeEnum.WEEK.getStartDate());
//        System.out.println(DateRangeEnum.WEEK.getEndDate());
//        System.out.println("----");
//        System.out.println(DateRangeEnum.MONTH.getStartDate());
//        System.out.println(DateRangeEnum.MONTH.getEndDate());
//        System.out.println("----");
//        System.out.println(DateRangeEnum.YEAR.getStartDate());
//        System.out.println(DateRangeEnum.YEAR.getEndDate());
//    }
}
