package com.serifgungor.firebasekullanimi.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
