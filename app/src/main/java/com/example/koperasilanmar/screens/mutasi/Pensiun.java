package com.example.koperasilanmar.screens.mutasi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.screens.home.Home;
import com.example.koperasilanmar.utils.router.services.ServiceUpdatePensiun;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Pensiun extends AppCompatActivity {

    private TextView notr,nost,tgl;
    private ImageView imageView;
    private MaterialButton materialButton;
    private String ids,notrs,nosts,tgls;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_pensiun);
        nost = findViewById(R.id.ST);
        notr= findViewById(R.id.TR);
        tgl= findViewById(R.id.keluar_stn);
        imageView = findViewById(R.id.back);
        materialButton = findViewById(R.id.ACC);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(b!=null)
        {
            ids = b.get("id").toString();
            notrs = b.get("no_tr").toString();
            nosts = b.get("no_st").toString();
            tgls = b.get("tgl").toString();
            nost.setText(nosts);
            notr.setText(notrs);
            tgl.setText(tgls);
        }
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Pensiun.this).setMessage("Apakah anda yakin ingin untuk ACC?")
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Map<String, Object> jsonParams = new ArrayMap<>();
                                jsonParams.put("id_pensiun",ids);
                                RequestBody body = RequestBody.create(MediaType.parse("application/json"),(new JSONObject(jsonParams)).toString());
                                ServiceUpdatePensiun serviceUpdateMutasi = new ServiceUpdatePensiun();
                                serviceUpdateMutasi.getModel(body).observe(Pensiun.this, new Observer<JsonObject>() {
                                    @Override
                                    public void onChanged(JsonObject jsonObject) {
                                        assert jsonObject != null;
                                        if (jsonObject.get("msg").toString().contains("Berhasil")){
                                            Toast.makeText(getApplicationContext(), jsonObject.get("msg").toString(), Toast.LENGTH_SHORT).show();

                                            Intent i = new Intent(Pensiun.this, Home.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(i);
                                            finish();
                                        }

                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
