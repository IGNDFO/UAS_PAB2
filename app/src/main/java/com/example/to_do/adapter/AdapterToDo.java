package com.example.to_do.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_do.API.APIRequestData;
import com.example.to_do.API.RetroServer;
import com.example.to_do.Activity.detail;
import com.example.to_do.Activity.ubah;
import com.example.to_do.R;
import com.example.to_do.model.ModelResponse;
import com.example.to_do.model.Model_To_Do;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterToDo extends RecyclerView.Adapter<AdapterToDo.VH_to_do>{
    private Context ctx;
    private List<Model_To_Do>list_todo;

    public  AdapterToDo(Context ctx, List<Model_To_Do> list_todo) {
        this.ctx = ctx;
        this.list_todo = list_todo;
    }

    @NonNull
    @Override
    public VH_to_do onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varview= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new VH_to_do(varview);
    }

    @Override
    public void onBindViewHolder(@NonNull VH_to_do holder, int position) {

        Model_To_Do mk=list_todo.get(position);
        holder.tvid.setText(mk.getId());
        holder.tvhari.setText(mk.getHari());
        holder.tvjudul.setText(mk.getJudul());
        holder.tvprioritas.setText(mk.getPrioritas());
        holder.tvisi.setText(mk.getIsi());

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent pindah = new Intent(ctx, detail.class);
        pindah.putExtra("xid",mk.getId());
        pindah.putExtra("xhari",mk.getHari());
        pindah.putExtra("xjudul",mk.getJudul());
        pindah.putExtra("xprioritas",mk.getPrioritas());
        pindah.putExtra("xisi",mk.getIsi());
        ctx.startActivity(pindah);
    }
});

    }

    @Override
    public int getItemCount() {
        return list_todo.size();
    }


    public class VH_to_do extends RecyclerView.ViewHolder{
        TextView tvid,tvjudul,tvprioritas,tvisi,tvhari;

        public VH_to_do(@NonNull View itemView) {
            super(itemView);
            tvid=  itemView.findViewById(R.id.tv_id);
            tvjudul=  itemView.findViewById(R.id.tv_judul);
            tvprioritas=  itemView.findViewById(R.id.tv_prioritas);
            tvisi=  itemView.findViewById(R.id.tv_isi);
            tvhari=  itemView.findViewById(R.id.tv_hari);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("KENAPA SIH");
                    pesan.setMessage("MAU NGAPAIN");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapus(tvid.getText().toString());
                            dialog.dismiss();
                        }
                    });
                    pesan.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, ubah.class);
                            pindah.putExtra("xid",tvid.getText().toString());
                            pindah.putExtra("xhari",tvhari.getText().toString());
                            pindah.putExtra("xjudul",tvjudul.getText().toString());
                            pindah.putExtra("xprioritas",tvprioritas.getText().toString());
                            pindah.putExtra("xisi",tvisi.getText().toString());



                            ctx.startActivity(pindah);
                        }
                    });
                    pesan.show();
                    return false;
                }
            });
        }

private void hapus(String id_todo){
            APIRequestData ARD= new RetroServer().konekretro().create(APIRequestData.class);
            Call<ModelResponse> proses=ARD.arddelete(id_todo);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "BERHASIL DI HAPUS PUAS?", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "GAGAL PANGGIL SERVER", Toast.LENGTH_SHORT).show();

                }
            });
}

    }

}
