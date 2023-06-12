package com.example.to_do.API;


import  com.example.to_do.model.ModelResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse>ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse>ardcreate(
            @Field("judul")String judul,
            @Field("isi")String asal,
            @Field("hari")String hari,
            @Field("prioritas")String prioritas

            );


    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse>ardupdate(
            @Field("id")String id,
            @Field("judul")String judul,
            @Field("isi")String asal,
            @Field("hari")String hari,
            @Field("prioritas")String prioritas
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse>arddelete(
            @Field("id")String id
    );

}
