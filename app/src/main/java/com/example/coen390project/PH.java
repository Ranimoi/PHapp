package com.example.coen390project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PH {
    private String MEASUREMENT_DATE;
    private Integer PH_VALUE;

    public PH(Integer PH_VALUE, String MEASUREMENT_DATE){
        this.MEASUREMENT_DATE = MEASUREMENT_DATE;
        this.PH_VALUE = PH_VALUE;
    }

    public PH(Integer PH_VALUE){
        this.PH_VALUE = PH_VALUE;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd @ HH:mm:ss");
        String MEASUREMENT_DATE = sdf.format(new Date());
        setMEASUREMENT_DATE(MEASUREMENT_DATE);
    }

    public String getMEASUREMENT_DATE() {
        return MEASUREMENT_DATE;
    }

    public void setMEASUREMENT_DATE(String MEASUREMENT_DATE) {
        this.MEASUREMENT_DATE = MEASUREMENT_DATE;
    }

    public Integer getPH_VALUE() {
        return PH_VALUE;
    }

    public void setPH_VALUE(Integer PH_VALUE) {
        this.PH_VALUE = PH_VALUE;
    }
}
