package com.zouag.fsdocs.networking;

/**
 * Created by Moham on 1/24/2016.
 */
public enum Method {
    POST("POST"), GET("GET");

    private String method = "";

    Method(String s) {
        method = s;
    }

    @Override
    public String toString() {
        return method;
    }
}
