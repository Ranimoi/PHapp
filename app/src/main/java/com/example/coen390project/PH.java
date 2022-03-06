package com.example.coen390project;

public class PH {
    private String MEASUREMENT_DATE;
    private Integer PH_VALUE;

    public PH(Integer PH_VALUE, String MEASUREMENT_DATE){
        this.MEASUREMENT_DATE = MEASUREMENT_DATE;
        this.PH_VALUE = PH_VALUE;
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
