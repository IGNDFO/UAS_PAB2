package com.example.to_do.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.to_do.API.APIRequestData;
import com.example.to_do.API.RetroServer;
import com.example.to_do.R;
import com.example.to_do.adapter.AdapterToDo;
import com.example.to_do.model.ModelResponse;
import com.example.to_do.model.Model_To_Do;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private RecyclerView rv_to_do;
private FloatingActionButton fab_tambah;
private ProgressBar pb;
private RecyclerView.Adapter ad_To_do;
private RecyclerView.LayoutManager lm_to_do;
private List<Model_To_Do> list_to_do=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_to_do = findViewById(R.id.rv_to_do);
        fab_tambah = findViewById(R.id.fab_tambah);
        pb = findViewById(R.id.pb);


         lm_to_do = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
         rv_to_do.setLayoutManager(lm_to_do);

        fab_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,tambah.class));
            }
        });
    }




    protected void  onResume(){
        super.onResume();
        retrieve_todo();
    }

    public void retrieve_todo (){
        pb.setVisibility(View.VISIBLE);

        APIRequestData ARD= new RetroServer().konekretro().create(APIRequestData.class);
        Call<ModelResponse> proses=ARD.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode =response.body().getKode();
                String  pesan =response.body().getPesan();
                list_to_do= response.body().getData();

                ad_To_do=new AdapterToDo(MainActivity.this,list_to_do);
                rv_to_do.setAdapter(ad_To_do);
                ad_To_do.notifyDataSetChanged();
                pb.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GAGAL MEMANGGIL SEVER", Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);

            }
        });


    }

    }
