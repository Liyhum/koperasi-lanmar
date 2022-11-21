package com.example.koperasilanmar.screens.menu.jurubayar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.adapter.GroupListUserAdapter;
import com.example.koperasilanmar.utils.model.users.ListUsers;
import com.example.koperasilanmar.utils.model.users.ResultUser;
import com.example.koperasilanmar.utils.router.services.ServiceListUsers;

import java.util.ArrayList;

public class ListUser extends AppCompatActivity {
    RecyclerView recyclerView;
    String id;
    ImageView imageView;
    ArrayList<ResultUser> modelLists;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_list_user);
        recyclerView = findViewById(R.id.list_user);
        modelLists = new ArrayList<>();
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b !=null){
            id = b.getString("stn");
        }
        imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ServiceListUsers serviceListUsers = new ServiceListUsers();
        serviceListUsers.getModel(id).observe(this, new Observer<ListUsers>() {
            @Override
            public void onChanged(ListUsers jsonObject) {
                if(jsonObject != null){
                    for (ResultUser item : jsonObject.getResult()){
                        ResultUser resultUser = new ResultUser();
                        resultUser = item;
                        modelLists.add(resultUser);
                        getAdapter();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Maaf Data ksoong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getAdapter(){
        GroupListUserAdapter groupMenuAdapter =new GroupListUserAdapter(this,this,modelLists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(groupMenuAdapter);
    }
}
