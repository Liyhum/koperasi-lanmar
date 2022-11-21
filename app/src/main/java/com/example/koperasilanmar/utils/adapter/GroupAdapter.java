package com.example.koperasilanmar.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.model.expended.User;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyView> {

    private ArrayList<User> modelLists;

    public GroupAdapter(ArrayList<User> modelLists){
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
        holder.namePangkat.setText(modelLists.get(position).getStatus());
        holder.numberShip.setText(modelLists.get(position).getPersonil() + " Personil");

    }

    @Override
    public int getItemCount() {
        if(modelLists != null){
            return modelLists.size();
        }
        return 0;
    }

}
