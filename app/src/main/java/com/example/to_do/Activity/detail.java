package com.example.to_do.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.to_do.R;

public class detail extends AppCompatActivity {

    TextView tvjudul,tvisi,tvid,tvprioritas,tvhari;
    private String yid,yjudul,yprioritas,yisi,yhari;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvid=  findViewById(R.id.tv_id);
        tvjudul=  findViewById(R.id.tv_judul);
        tvprioritas=  findViewById(R.id.tv_prioritas);
        tvisi=  findViewById(R.id.tv_isi);
        tvhari=  findViewById(R.id.tv_hari);

        Intent ambil=getIntent();
        yid=ambil.getStringExtra("xid");
        yjudul=ambil.getStringExtra("xjudul");
        yisi=ambil.getStringExtra("xisi");
        yhari=ambil.getStringExtra("xhari");
        yprioritas=ambil.getStringExtra("xprioritas");

        tvjudul.setText(yjudul);
        tvisi.setText(yisi);
        tvhari.setText(yhari);
        tvprioritas.setText(yprioritas);


    }
}