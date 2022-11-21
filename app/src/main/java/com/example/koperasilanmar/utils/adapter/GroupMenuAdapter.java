package com.example.koperasilanmar.utils.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.model.admin.menu.User;
import com.example.koperasilanmar.utils.model.mutasi_pensiun.Mutasi;

import java.util.ArrayList;

public class GroupMenuAdapter extends RecyclerView.Adapter<GroupMenuAdapter.MyView> {

    private ArrayList<Mutasi> modelLists;
    private Activity adminAll;
    private ArrayList<User> users;

    public GroupMenuAdapter(Activity adminAll,ArrayList<Mutasi> modelLists){
        this.adminAll = adminAll;
        this.modelLists = modelLists;
    }
    public class MyView extends RecyclerView.ViewHolder {

        private TextView namePangkat;
        private RecyclerView recyclerView;
        public MyView(@NonNull View itemView) {
            super(itemView);
            namePangkat = itemView.findViewById(R.id.status);
            recyclerView = itemView.findViewById(R.id.users);
            users = new ArrayList<>();
            if ( modelLists.get(0).getUsers() == null){
                modelLists = new ArrayList<>();
            }
        }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_group,parent,false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        if(modelLists != null){
            holder.namePangkat.setText(modelLists.get(position).getStatus());
            User user;
            for (User item :modelLists.get(position).getUsers()){
                user = item;
                users.add(user);
            }
            UsersAdapter usersAdapter = new UsersAdapter(adminAll,users);

            LinearLayoutManager layoutManager2 = new LinearLayoutManager(adminAll.getApplicationContext());

            holder.recyclerView.setLayoutManager(layoutManager2);
            holder.recyclerView.setAdapter(usersAdapter);
        }else {
            Toast.makeText(adminAll, "Maaf Data mutasi masih kosong", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        if(modelLists != null){
            return modelLists.size();
        }
        return 0;
    }

}
