package com.serifgungor.firebasekullanimi.Adapter;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.serifgungor.firebasekullanimi.Holder.RecyclerViewHolder;
import com.serifgungor.firebasekullanimi.Model.Paylasim;
import com.serifgungor.firebasekullanimi.R;

import java.util.ArrayList;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(Paylasim paylasim);
    }
    OnItemClickListener listener;

    private ArrayList<Paylasim> paylasimlar;
    private Context context;

    public RecyclerViewAdapter(){

    }

    public RecyclerViewAdapter(Context context,ArrayList<Paylasim> paylasimlar,OnItemClickListener onItemClickListener){
        this.context = context;
        this.paylasimlar = paylasimlar;
        this.listener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /*
        Satır görüntüsünün bir layout'a bağlanması işlemini sağlar.
         */
        View v = LayoutInflater.from(context).inflate(R.layout.holder_view,viewGroup,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, final int i) {
        /*
        Satır görüntüsünün doldurulduğu alanı alanı ifade eder
         */
        recyclerViewHolder.tvPaylasimYazisi.setText(paylasimlar.get(i).getIcerik());
        String tarih = new Date().toString();
        recyclerViewHolder.tvPaylasimTarihi.setText(tarih);

        recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(paylasimlar.get(i));
            }
        });


    }

    @Override
    public int getItemCount() {
        return paylasimlar.size();
    }

}
