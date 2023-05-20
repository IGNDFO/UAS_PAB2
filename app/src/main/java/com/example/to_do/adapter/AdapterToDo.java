package com.example.to_do.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_do.R;
import com.example.to_do.model.ModelResponse;
import com.example.to_do.model.Model_To_Do;

import java.util.List;

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
        holder.tvjudul.setText(mk.getJudul());
        holder.tvprioritas.setText(mk.getPrioritas());
        holder.tvisi.setText(mk.getIsi());

    }

    @Override
    public int getItemCount() {
        return list_todo.size();
    }


    public class VH_to_do extends RecyclerView.ViewHolder{
        TextView tvid,tvjudul,tvprioritas,tvisi;

        public VH_to_do(@NonNull View itemView) {
            super(itemView);
            tvid=  itemView.findViewById(R.id.tv_id);
            tvjudul=  itemView.findViewById(R.id.tv_judul);
            tvprioritas=  itemView.findViewById(R.id.tv_prioritas);
            tvisi=  itemView.findViewById(R.id.tv_prioritas);

        }
    }

}
