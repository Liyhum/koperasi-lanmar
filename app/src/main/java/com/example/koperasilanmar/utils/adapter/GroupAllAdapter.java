package com.example.koperasilanmar.utils.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.screens.menu.jurubayar.ListUser;
import com.example.koperasilanmar.utils.model.expended.Personil;
import com.example.koperasilanmar.utils.model.expended.Simpanan;
import com.example.koperasilanmar.utils.model.expended.User;

import java.util.ArrayList;

public class GroupAllAdapter extends RecyclerView.Adapter<GroupAllAdapter.MyView> {

    private ArrayList<Personil> modelLists;
    private ArrayList<User> personils;
    private ArrayList<Simpanan> simpanans;
    private Context activity;
    private Activity activity2;
    boolean expended = true;
    String role,stn_kerja;

    public GroupAllAdapter(Context activity, Activity activity2,ArrayList<Personil> modelLists,String role){
        this.activity = activity;
        this.activity2 = activity2;
        this.role = role;
        this.modelLists = modelLists;
    }
    public class MyView extends RecyclerView.ViewHolder {

        private TextView namePangkat,judul;
        private RecyclerView listPngkt,listSimpanan;
        private LinearLayout linearLayout;
        public MyView(@NonNull View itemView) {
            super(itemView);
            namePangkat = itemView.findViewById(R.id.nama_pngkt);
            listPngkt = itemView.findViewById(R.id.pngktList);
            listSimpanan = itemView.findViewById(R.id.simpananList);
            judul = itemView.findViewById(R.id.judul);
            linearLayout = itemView.findViewById(R.id.sub_item);
            personils = new ArrayList<>();
            simpanans = new ArrayList<>();
            if (role.equals("Admin")) {
                linearLayout.setVisibility(expended ? View.GONE : View.VISIBLE);
            } else {
                linearLayout.setVisibility(View.VISIBLE);
                namePangkat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent  = new Intent(activity, ListUser.class);
                        intent.putExtra("stn",stn_kerja != null ? stn_kerja :"Ga ada");
                        activity.startActivity(intent);
                    }
                });
            }
        }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_group_all,parent,false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
        if(!modelLists.isEmpty()){
            for (User user : modelLists.get(position).getUsers()){
//            Log.d(TAG, "onBindViewHolder: " + user.getStatus());
                User user1 = new User();
                user1.setPersonil(user.getPersonil());
                user1.setStatus(user.getStatus());
                if(user.getStatus().equals("Militer") || user.getStatus().equals("PNS")){
                }else {
                    personils.add(user1);
                }
            }
            if (modelLists.get(position).getSimpanan() != null){
                for (Simpanan user : modelLists.get(position).getSimpanan()){
                    Simpanan user1 = new Simpanan();
                    user1.setSimpanan(user.getSimpanan());
                    user1.setStatus(user.getStatus());
                    if(user.getStatus().equals("Militer") || user.getStatus().equals("PNS")){
                    }else {
                        simpanans.add(user1);
                    }
                }
            }
        }else{
            Toast.makeText(activity, "Maaf Data Kosong", Toast.LENGTH_SHORT).show();
        }

        if (role.equals("Admin")){
            holder.namePangkat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expended = !expended;
                    notifyItemChanged(position);
                    personils.clear();
                    simpanans.clear();
                }
            });

        }
        holder.judul.setText("PERSONIL "+modelLists.get(position).getStatus() +" BULAN INI");
        stn_kerja = modelLists.get(position).getStatus();
        GroupAdapter groupAdapter = new GroupAdapter(personils);
        SimpananAdapter simpananAdapter = new SimpananAdapter(simpanans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(activity);
        holder.listPngkt.setLayoutManager(layoutManager);
        holder.listPngkt.setAdapter(groupAdapter);
        holder.listSimpanan.setLayoutManager(layoutManager2);
        holder.listSimpanan.setAdapter(simpananAdapter);
        holder.namePangkat.setText(modelLists.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        if(modelLists != null){
            return modelLists.size();
        }
        return 0;
    }

}
