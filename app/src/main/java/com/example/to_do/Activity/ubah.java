package com.example.to_do.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to_do.API.APIRequestData;
import com.example.to_do.API.RetroServer;
import com.example.to_do.R;
import com.example.to_do.model.ModelResponse;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ubah extends AppCompatActivity {
    private EditText etjudul,etisi,etprioritas;
    private Button btnedit;
    private MaterialButton ethari;
    private String choosenDate;
    private DatePickerDialog dpd;
    private String yid,yjudul,yprioritas,yisi,yhari;
    private  String judul,isi,hari,prioritas,tahun,bulan,tanggal;
    private SimpleDateFormat dateFormatter;
    Locale localeID = new Locale("in", "ID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        Intent ambil=getIntent();
        yid=ambil.getStringExtra("xid");
        yprioritas=ambil.getStringExtra("xprioritas");
        yhari=ambil.getStringExtra("xhari");
        yjudul=ambil.getStringExtra("xjudul");
        yisi=ambil.getStringExtra("xisi");


        etjudul=findViewById(R.id.et_judul);
        etisi=findViewById(R.id.et_isi);
        ethari=findViewById(R.id.et_hari);
        etprioritas=findViewById(R.id.et_prioritas);
        btnedit=findViewById(R.id.btn_ubah);



        etprioritas.setText(yprioritas);
        etjudul.setText(yjudul);
        etisi.setText(yisi);
        ethari.setText(yhari);


        ethari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar kalender = Calendar.getInstance();
                dpd = new DatePickerDialog
                        (ubah.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                tahun = Integer.toString(year);
                                bulan = Integer.toString(month + 1);
                                tanggal = Integer.toString(day);
                                choosenDate =  tanggal+"/" + bulan + "/" +tahun ;
                                ethari.setText(choosenDate);
                                ethari.setError(null);
                            }
                        }
                                , kalender.get(Calendar.YEAR)
                                , kalender.get(Calendar.MONTH)
                                , kalender.get(Calendar.DAY_OF_MONTH));
                dpd.show();

            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prioritas=etprioritas.getText().toString();
                judul=etjudul.getText().toString();
                isi=etisi.getText().toString();
                hari=ethari.getText().toString();
                prioritas=etprioritas.getText().toString();
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd", localeID);

                if(judul.trim().isEmpty()){
                    etjudul.setError("JUDULNYA APA ");
                } else if (isi.trim().isEmpty()) {
                    etisi.setError("TERUS U MAU NGAPAIN");
                }else if (hari.trim().isEmpty()) {
                    ethari.setError("IYA HARI APAAA");
                }
                else if (prioritas.trim().isEmpty()) {
                    etprioritas.setError("SEBERAPA PENTING ITUUU");
                }
                else{
                    edit_to_do();
                }
            }
        });
    }

    private void edit_to_do(){
        APIRequestData ARD= new RetroServer().konekretro().create(APIRequestData.class);
        Call<ModelResponse> proses= ARD.ardupdate(yid,judul,isi,hari,prioritas);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode= response.body().getKode();
                String pesan= response.body().getPesan();
                Toast.makeText(ubah.this, "BERHASIL EDIT DATA", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(ubah.this, "GAGAL MENGHUBUNGI SERVER :(", Toast.LENGTH_SHORT).show();
            }
        });
    }


    }
