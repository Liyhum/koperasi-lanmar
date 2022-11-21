package com.example.koperasilanmar.screens.mutasi;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.screens.home.Home;
import com.example.koperasilanmar.utils.router.services.ServiceKirimMutasi;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MutasiKirim extends AppCompatActivity {

    private EditText notr,nost;
    private TextView tgl;

    Calendar myCalendar;
    private ImageView imageView;
    private MaterialButton materialButton;
    private String ids,notrs,nosts;


    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_kirim_mutasi);
        nost = findViewById(R.id.ST);
        notr= findViewById(R.id.TR);
        tgl= findViewById(R.id.keluar_stn);
        imageView = findViewById(R.id.back);
        materialButton = findViewById(R.id.kirim);
        myCalendar = Calendar.getInstance();
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
            nost.setText(nosts);
            notr.setText(notrs);
            nost.setEnabled(false);
            nost.setKeyListener(null);
            notr.setKeyListener(null);
            notr.setEnabled(false);
        }
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MutasiKirim.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MutasiKirim.this).setMessage("Apakah anda yakin ingin untuk Mutasi?")
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(!ids.isEmpty()){
                                    Map<String, Object> jsonParams = new ArrayMap<>();
                                    jsonParams.put("id_users",ids);
                                    jsonParams.put("no_tr",notrs);
                                    jsonParams.put("no_st",nosts);
                                    jsonParams.put("keluar_stn",tgl.getText().toString());
                                    RequestBody body = RequestBody.create(MediaType.parse("application/json"),(new JSONObject(jsonParams)).toString());
                                    ServiceKirimMutasi serviceUpdateMutasi = new ServiceKirimMutasi();
                                    if(!tgl.getText().toString().equals("2001 / 03 / 03")){
                                        serviceUpdateMutasi.getModel(body).observe(MutasiKirim.this, new Observer<JsonObject>() {
                                            @Override
                                            public void onChanged(JsonObject jsonObject) {
                                                assert jsonObject != null;
                                                Log.d(TAG, "onChanged: "+jsonObject);
                                                if (jsonObject.get("msg").toString().contains("Berhasil")){
                                                    Toast.makeText(getApplicationContext(), jsonObject.get("msg").toString(), Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(MutasiKirim.this, Home.class);
                                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(i);
                                                    finish();
                                                }else {
                                                    new AestheticDialog.Builder(MutasiKirim.this, DialogStyle.EMOTION, DialogType.ERROR)
                                                            .setTitle("Error")
                                                            .setMessage(jsonObject.get("msg").toString())
                                                            .setGravity(Gravity.CENTER)
                                                            .setAnimation(DialogAnimation.CARD)
                                                            .show();
                                                }
                                            }
                                        });
                                    }else {
                                        new AestheticDialog.Builder(MutasiKirim.this, DialogStyle.RAINBOW, DialogType.ERROR)
                                                .setTitle("Error")
                                                .setMessage("Silahkan masukan tanggal")
                                                .setGravity(Gravity.CENTER)
                                                .setAnimation(DialogAnimation.CARD)
                                                .show();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void updateLabel(){
        String myFormat="yyyy/MM/dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        dateFormat.setTimeZone(tz);
        tgl.setText(dateFormat.format(myCalendar.getTime()));
    }
}
