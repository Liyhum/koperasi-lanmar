package com.example.koperasilanmar.utils.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.koperasilanmar.R;
import com.example.koperasilanmar.screens.mutasi.Pensiun;
import com.example.koperasilanmar.utils.Constants;
import com.example.koperasilanmar.utils.model.mutasi_pensiun.User__1;

import java.util.ArrayList;

public class UsersPenAdapter extends RecyclerView.Adapter<UsersPenAdapter.MyView> {

    private ArrayList<User__1> modelLists;
    private Activity adminAll;

    public UsersPenAdapter(Activity adminAll, ArrayList<User__1> modelLists){
        this.adminAll = adminAll;
        this.modelLists = modelLists;
    }


    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private TextView npp;
        private TextView status;
        private ImageView imageView;
        public MyView(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name);
            npp = itemView.findViewById(R.id.npp);
            status = itemView.findViewById(R.id.status);
            imageView = itemView.findViewById(R.id.avatar);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(adminAll, Pensiun.class);
            intent.putExtra("id",modelLists.get(getLayoutPosition()).getIdPensiun());
            intent.putExtra("no_tr",modelLists.get(getLayoutPosition()).getNoTr());
            intent.putExtra("no_st",modelLists.get(getLayoutPosition()).getNoSt());
            intent.putExtra("tgl",modelLists.get(getLayoutPosition()).getKeluarStn());
            adminAll.startActivity(intent);

        }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent,false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.name.setText(modelLists.get(position).getNamaLengkap());
        holder.npp.setText(modelLists.get(position).getNpp());
        holder.status.setText(modelLists.get(position).getStatus());
        String url = Constants.base_url + "image/" + modelLists.get(position).getImgUser();
        Glide.with(adminAll).load(Uri.parse(url)).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        if(modelLists != null){
            return modelLists.size();
        }
        return 0;
    }

}
