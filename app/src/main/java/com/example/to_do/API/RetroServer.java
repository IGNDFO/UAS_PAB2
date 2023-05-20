package com.example.to_do.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String alamat_url="https://oriell754.000webhostapp.com/UAS_PAB2/";
    private  static Retrofit retro;

    public static Retrofit konekretro(){
        if (retro==null){
            retro=new Retrofit.Builder().baseUrl(alamat_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retro;
    }
}