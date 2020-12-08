package com.example.coloreffect;

import java.io.Serializable;

public class DataForBundle implements Serializable {

    private static final long serialVersionUID = 1L;

    String resultPressure;
    String resultTomorrow;
    String resultWeek;
    String message;
    int photoWeather;

    public DataForBundle(String resultPressure, String resultTomorrow, String resultWeek, String message, int photoWeather) {
        this.resultPressure = resultPressure;
        this.resultTomorrow = resultTomorrow;
        this.resultWeek = resultWeek;
        this.message = message;
        this.photoWeather = photoWeather;
    }

    public String getResultPressure() {
        return resultPressure;
    }

    public void setResultPressure(String resultPressure) {
        this.resultPressure = resultPressure;
    }

    public String getResultTomorrow() {
        return resultTomorrow;
    }

    public void setResultTomorrow(String resultTomorrow) {
        this.resultTomorrow = resultTomorrow;
    }

    public String getResultWeek() {
        return resultWeek;
    }

    public void setResultWeek(String resultWeek) {
        this.resultWeek = resultWeek;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPhotoWeather() {
        return photoWeather;
    }

    public void setPhotoWeather(int photoWeather) {
        this.photoWeather = photoWeather;
    }
}
