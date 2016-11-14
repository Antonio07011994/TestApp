package com.example.antonio.testapp;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by antonio on 08.11.16.
 */
public interface GetJSON {
    @GET("/data.json")
    Call<JSON> getData();
}
