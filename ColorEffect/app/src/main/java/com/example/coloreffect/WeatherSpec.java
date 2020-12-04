package com.example.coloreffect;

import android.content.Context;

final class WeatherSpec {// final запрещает наследоваться от класса
    private WeatherSpec(){//приватный конструктор запрещает создавать экземпляры класса
    }

    static String getWeather(Context context, int position) {
        String[] weather = context.getResources().getStringArray(R.array.cityes_weather);
        return weather[position];


    }

    static String getPressure(Context context, int position) {
        String[] pressure = context.getResources().getStringArray(R.array.pressure);
        return pressure[position];


    }

    static String getTomorrow(Context context, int position) {
        String[] tomorrow = context.getResources().getStringArray(R.array.tomorrow);
        return tomorrow[position];


    }

    static String getWeek(Context context, int position) {
        String[] week = context.getResources().getStringArray(R.array.week);
        return week[position];


    }


}
