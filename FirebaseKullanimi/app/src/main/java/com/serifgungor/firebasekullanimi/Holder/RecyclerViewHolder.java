package com.serifgungor.firebasekullanimi.Holder;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.serifgungor.firebasekullanimi.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView tvPaylasimYazisi;
    public TextView tvPaylasimTarihi;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        tvPaylasimYazisi=itemView.findViewById(R.id.txtPaylasimYazisi);
        tvPaylasimTarihi=itemView.findViewById(R.id.txtPaylasimTarih);
    }
}
