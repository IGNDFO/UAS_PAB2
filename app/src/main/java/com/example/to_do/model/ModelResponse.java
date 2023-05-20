package com.example.to_do.model;


import java.util.List;

public class ModelResponse {
    private String kode,pesan;
    private List<Model_To_Do> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<Model_To_Do> getData() {
        return data;
    }
}