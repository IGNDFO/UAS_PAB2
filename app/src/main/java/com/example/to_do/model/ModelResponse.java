package com.example.to_do.model;


import java.util.List;

public class ModelResponse {
    private String kode,pesan;
    private List<ModelResponse> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelResponse> getData() {
        return data;
    }
}