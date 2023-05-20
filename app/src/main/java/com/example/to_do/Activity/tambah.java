package com.example.to_do.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.to_do.API.APIRequestData;
import com.example.to_do.API.RetroServer;
import com.example.to_do.R;
import com.example.to_do.model.ModelResponse;

import retrofit2.Call;

public class tambah extends AppCompatActivity {

    private EditText etjudul,etisi,ethari,etprioritas;
    private Button btntambah;
    private  String judul,isi,hari,prioritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        etjudul=findViewById(R.id.et_judul);
        etisi=findViewById(R.id.et_isi);
        ethari=findViewById(R.id.et_hari);
        etprioritas=findViewById(R.id.et_prioritas);
        btntambah=findViewById(R.id.btn_tambah);

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul=etjudul.getText().toString();
                isi=etisi.getText().toString();
               hari=ethari.getText().toString();
                prioritas=etprioritas.getText().toString();
                if(judul.trim().isEmpty()){
                    etjudul.setError("Nama Tidak Boleh Kosong");
                } else if (isi.trim().isEmpty()) {
                    etisi.setError("Asal Tidak Boleh Kosong");
                }else if (hari.trim().isEmpty()) {
                    ethari.setError("Deskripsi Singkat Tidak Boleh Kosong");
                }
                else if (prioritas.trim().isEmpty()) {
                etprioritas.setError("Deskripsi Singkat Tidak Boleh Kosong");
                 }
                else{
                    tambahisi();
                }
            }
        });
    }
    private void tambahisi(){
        APIRequestData ARD= new RetroServer().konekretro().create(APIRequestData.class);
        Call<ModelResponse> proses= ARD.ardcreate(judul,isi,hari,prioritas);


    }
}