package com.example.to_do.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.to_do.R;

public class about extends AppCompatActivity {
    private ImageView  ivFotoNando ,ivMdp;
    private TextView namaNando,tvabout,tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("ABOUT US");


        ivFotoNando = findViewById(R.id.iv_foto_nando);
        ivMdp= findViewById(R.id.iv_mdp);


        namaNando=findViewById(R.id.nama_nando);

        tvabout= findViewById(R.id.tv_about);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
    }
}