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
import com.example.koperasilanmar.utils.model.mutasi_pensiun.Pensiun;
import com.example.koperasilanmar.utils.model.mutasi_pensiun.User__1;

import java.util.ArrayList;

public class GroupPenMenuAdapter extends RecyclerView.Adapter<GroupPenMenuAdapter.MyView> {

    private ArrayList<Pensiun> modelLists;
    private Activity adminAll;
    private ArrayList<User__1> users;

    public GroupPenMenuAdapter(Activity adminAll, ArrayList<Pensiun> modelLists){
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
        if(modelLists.size() > 0){
            holder.namePangkat.setText(modelLists.get(position).getStatus());
            User__1 user;
            for (User__1 item: modelLists.get(position).getUsers()){
                user = item;
                users.add(user);
            }
            UsersPenAdapter usersAdapter = new UsersPenAdapter(adminAll,users);

            LinearLayoutManager layoutManager2 = new LinearLayoutManager(adminAll.getApplicationContext());

            holder.recyclerView.setLayoutManager(layoutManager2);
            holder.recyclerView.setAdapter(usersAdapter);
        }else {
            Toast.makeText(adminAll, "Maaf Data pensiunan masih kosong", Toast.LENGTH_SHORT).show();
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
