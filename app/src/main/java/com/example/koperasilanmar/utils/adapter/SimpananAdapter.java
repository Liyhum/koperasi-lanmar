package com.example.koperasilanmar.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.model.expended.Simpanan;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SimpananAdapter extends RecyclerView.Adapter<SimpananAdapter.MyView> {

    private ArrayList<Simpanan> modelLists;
    public SimpananAdapter(ArrayList<Simpanan> modelLists){
        this.modelLists = modelLists;
    }
    public class MyView extends RecyclerView.ViewHolder {
        private TextView namePangkat;
        private TextView numberShip;
        public MyView(@NonNull View itemView) {
            super(itemView);
            namePangkat = itemView.findViewById(R.id.nama_pngkt);
            numberShip = itemView.findViewById(R.id.jumlah);
        }
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_personil,parent,false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        if(!modelLists.get(position).getSimpanan().isEmpty()){
            holder.numberShip.setText(formatRupiah(Double.parseDouble(modelLists.get(position).getSimpanan())));
            holder.namePangkat.setText(modelLists.get(position).getStatus());
        }
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
    @Override
    public int getItemCount() {
        if(modelLists != null){
            return modelLists.size();
        }
        return 0;
    }

}
