package com.serifgungor.firebasekullanimi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serifgungor.firebasekullanimi.Holder.RecyclerViewHolder;
import com.serifgungor.firebasekullanimi.Model.Paylasim;
import com.serifgungor.firebasekullanimi.R;

import java.util.ArrayList;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Paylasim> paylasimlar;
    private Context context;

    public RecyclerViewAdapter(){

    }

    public RecyclerViewAdapter(Context context,ArrayList<Paylasim> paylasimlar){
        this.context = context;
        this.paylasimlar = paylasimlar;
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
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        /*
        Satır görüntüsünün doldurulduğu alanı alanı ifade eder
         */
        recyclerViewHolder.tvPaylasimYazisi.setText(paylasimlar.get(i).getIcerik());
        String tarih = new Date().toString();
        recyclerViewHolder.tvPaylasimTarihi.setText(tarih);

    }

    @Override
    public int getItemCount() {
        return paylasimlar.size();
    }

}
