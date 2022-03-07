package com.example.coen390project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PH {
    private String MEASUREMENT_DATE;
    private Float PH_VALUE;

    public PH(Float PH_VALUE, String MEASUREMENT_DATE){
        this.MEASUREMENT_DATE = MEASUREMENT_DATE;
        this.PH_VALUE = PH_VALUE;
    }

    public PH(Float PH_VALUE){
        this.PH_VALUE = PH_VALUE;

        //get the timestamp to store it in the table
        Calendar calendar;
        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy.MM.dd '@' HH:mm:ss");
        simpleDate.setTimeZone(TimeZone.getTimeZone("EST"));
        String dateTime = (simpleDate.format(calendar.getTime()).toString());
        setMEASUREMENT_DATE(dateTime);

    }



    public String getMEASUREMENT_DATE() {
        return MEASUREMENT_DATE;
    }

    public void setMEASUREMENT_DATE(String MEASUREMENT_DATE) {
        this.MEASUREMENT_DATE = MEASUREMENT_DATE;
    }

    public Float getPH_VALUE() {
        return PH_VALUE;
    }

    public void setPH_VALUE(Float PH_VALUE) {
        this.PH_VALUE = PH_VALUE;
    }
}
