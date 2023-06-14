package com.example.to_do.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.to_do.API.APIRequestData;
import com.example.to_do.API.RetroServer;
import com.example.to_do.R;
import com.example.to_do.model.ModelResponse;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tambah extends AppCompatActivity {

    private EditText etjudul,etisi,etprioritas;
    private Button btntambah ;
    private MaterialButton ethari;
    private  String judul,isi,hari,prioritas,tahun,bulan,tanggal;
    private SimpleDateFormat dateFormatter;
   private String choosenDate;
   private  DatePickerDialog dpd;
    Locale localeID = new Locale("in", "ID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etjudul=findViewById(R.id.et_judul);
        etisi=findViewById(R.id.et_isi);
        ethari=findViewById(R.id.et_hari);
        etprioritas=findViewById(R.id.et_prioritas);
        btntambah=findViewById(R.id.btn_tambah);
        ethari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Calendar kalender = Calendar.getInstance();
               dpd = new DatePickerDialog
                        (tambah.this, new DatePickerDialog.OnDateSetListener() {
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

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul=etjudul.getText().toString();
                isi=etisi.getText().toString();

               hari=ethari.getText().toString();

                prioritas=etprioritas.getText().toString();
//                dateFormatter = new SimpleDateFormat("yyyy-MM-dd", localeID);
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
                    tambahisi();
                }
            }
        });
    }
    private void tambahisi(){
        APIRequestData ARD= new RetroServer().konekretro().create(APIRequestData.class);
        //kalo5 field kok error
        Call<ModelResponse> proses= ARD.ardcreate(judul,isi,hari,prioritas);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode= response.body().getKode();
                String pesan= response.body().getPesan();
                Toast.makeText(tambah.this, "Kode" + kode +"pesan"+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(tambah.this, "GAGAL MENGHUBUNGI SERVER :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}