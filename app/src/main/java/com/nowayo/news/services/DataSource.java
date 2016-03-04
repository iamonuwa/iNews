package com.nowayo.news.services;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by matrix on 03/03/2016.
 */
public interface DataSource {


    @GET("/app/index{id}")
    void getPostById(@Path("id") int id, Callback<AppData> rb);

    @GET("/app/index")
    void getAllPosts(@Path("") int id, Callback<AppData> rb);
}
