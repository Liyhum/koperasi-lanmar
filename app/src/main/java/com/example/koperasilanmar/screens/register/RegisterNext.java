package com.example.koperasilanmar.screens.register;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.screens.login.Login;
import com.example.koperasilanmar.utils.router.services.ServiceRegisterModel;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegisterNext extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText notr,nost,npp,noHp,email,pass,pass2,gaji,nokted;
    TextView msk_satuan,nokt;
    RadioGroup radioGroup;
    Spinner pngkt_gol,stn_kerja;
    MaterialButton daftar;
    Calendar myCalendar;
    //Register next
    String status,pngkt;
    LinearLayout lay_noKt;
    RelativeLayout lay_notr,laynpp;
    int stn;

    //extra put
    String nama,jk,tgl_lahir,img,alamat;
    private boolean passwordShown;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_register_user);
        lay_notr = findViewById(R.id.lay_noTr);
        laynpp = findViewById(R.id.lay_npp);
        lay_noKt = findViewById(R.id.Lay_noKt);
        nokt = findViewById(R.id.textNokt);
        nokted = findViewById(R.id.no_kt);
        notr = findViewById(R.id.NoTr);
        nost = findViewById(R.id.NoSt);
        npp = findViewById(R.id.npp);
        noHp = findViewById(R.id.NoHP);
        email = findViewById(R.id.Email);
        pass = findViewById(R.id.password);
        pass2 = findViewById(R.id.password2);
        gaji = findViewById(R.id.gaji_bersih);
        msk_satuan  = findViewById(R.id.tgl_lahir);
        radioGroup = findViewById(R.id.radio_pngkt_gol);
        pngkt_gol = findViewById(R.id.pngkt_gol);
        stn_kerja = findViewById(R.id.stn_kerja);
        daftar = findViewById(R.id.kirim);
        myCalendar = Calendar.getInstance();
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        lay_noKt.setVisibility(View.GONE);
        if(b!=null)
        {
           nama = b.get("name").toString();
           jk = b.get("jk").toString();
           tgl_lahir = b.get("tgl_lahir").toString();
           alamat = b.get("alamat").toString();
           img = b.get("img").toString();

        }
        setOnClik();
        setspinner();
    }

    private void setspinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, R.layout.list_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pngkt_gol.setAdapter(adapter);

        pngkt_gol.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.satuan_kerja_array,R.layout.list_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stn_kerja.setAdapter(adapter2);
        stn_kerja.setOnItemSelectedListener(this);
    }

    private void updateLabel(){
        String myFormat="yyyy/MM/dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        dateFormat.setTimeZone(tz);
        msk_satuan.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void postDaftar(){
        daftar.setText("Loading.....");
        daftar.setClickable(false);
        ServiceRegisterModel serviceLoginModel = new ServiceRegisterModel();
        File file = new File(img);
        int simp = pngkt.equals("Perwira") ? status.equals("Honorer")? 25000:100000 : pngkt.equals("Gol") ? 25000:50000;
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"),nama);
        RequestBody jks = RequestBody.create(MediaType.parse("text/plain"),jk);
        RequestBody tgl_lahirs = RequestBody.create(MediaType.parse("text/plain"),tgl_lahir);
        RequestBody alamats = RequestBody.create(MediaType.parse("text/plain"),alamat);
        RequestBody notrs = RequestBody.create(MediaType.parse("text/plain"),(status.equals("Honorer")) ? "" : notr.getText().toString().trim());
        RequestBody nosts = RequestBody.create(MediaType.parse("text/plain"),(status.equals("Honorer")) ? "" : nost.getText().toString().trim());
        RequestBody nokts = RequestBody.create(MediaType.parse("text/plain"), nokted.getText().toString().trim());
        RequestBody msk_st = RequestBody.create(MediaType.parse("text/plain"), msk_satuan.getText().toString().trim());
        RequestBody statuss = RequestBody.create(MediaType.parse("text/plain"),status);
        RequestBody pngkts = RequestBody.create(MediaType.parse("text/plain"),(status.equals("Honorer")) ? "" :pngkt);
        RequestBody nohp = RequestBody.create(MediaType.parse("text/plain"), noHp.getText().toString().trim());
        RequestBody emails = RequestBody.create(MediaType.parse("text/plain"), email.getText().toString().trim());
        RequestBody npps = RequestBody.create(MediaType.parse("text/plain"), npp.getText().toString().trim());
        RequestBody gajis = RequestBody.create(MediaType.parse("text/plain"), gaji.getText().toString().trim());
        RequestBody username = RequestBody.create(MediaType.parse("text/plain"),nama);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"),pass.getText().toString());
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"),file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("dataimage",file.getName(),mFile);
        serviceLoginModel.getModel(RegisterNext.this,name,jks,tgl_lahirs,alamats,notrs,nosts,nokts,msk_st,statuss,pngkts,nohp,emails,3,gajis,stn,0,username,password,body,npps).observe(this, new Observer<JsonObject>() {
            @Override
            public void onChanged(JsonObject jsonObject) {
                if(jsonObject.get("msg").toString().contains("Anda")){
                    daftar.setClickable(true);
                    new AestheticDialog.Builder(RegisterNext.this, DialogStyle.FLAT, DialogType.SUCCESS)
                            .setTitle("Success")
                            .setMessage(jsonObject.get("msg").toString())
                            .setCancelable(true)
                            .setGravity(Gravity.CENTER)
                            .setAnimation(DialogAnimation.SHRINK)
                            .setOnClickListener(new OnDialogClickListener() {
                                @Override
                                public void onClick(AestheticDialog.Builder builder) {
                                    Intent intent = new Intent(RegisterNext.this, Login.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();
                }else {
                    new AestheticDialog.Builder(RegisterNext.this, DialogStyle.FLAT,DialogType.ERROR)
                            .setTitle("Error")
                            .setMessage(jsonObject.get("msg").toString())
                            .setGravity(Gravity.CENTER)
                            .show();
                    daftar.setClickable(true);
                    daftar.setText("Kirim");
                }
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    private void setOnClik(){
        // Initalize Date Picker
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        msk_satuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterNext.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //DatePicker
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                status = checkedRadioButton.getText().toString();
                if (status.equals("Honorer")){
                    lay_noKt.setVisibility(View.VISIBLE);
                    laynpp.setVisibility(View.GONE);
                    lay_notr.setVisibility(View.GONE);
                }else {
                    lay_noKt.setVisibility(View.GONE);
                    laynpp.setVisibility(View.VISIBLE);
                    lay_notr.setVisibility(View.VISIBLE);
                }
            }
        });
        pass.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(motionEvent.getRawX() >= (pass.getRight() - pass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(passwordShown){
                            passwordShown = false;
                            pass.setInputType(129);
                        }else{
                            passwordShown = true;
                            pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }

                        return true;
                    }
                }
                return false;
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if(status.equals("Honorer")){
                       if(status == null || stn == 0||nokt.getText().toString().isEmpty()||gaji.getText().toString().isEmpty() || pass.getText().toString().isEmpty()||pass2.getText().toString().isEmpty()){
                           new AestheticDialog.Builder(RegisterNext.this, DialogStyle.TOASTER,DialogType.ERROR)
                                   .setTitle("Error")
                                   .setMessage("Silahkan Semua di isi dengan baik")
                                   .setGravity(Gravity.CENTER)
                                   .show();
                       }else {
                           if(!pass.getText().toString().equals(pass2.getText().toString())) {
                               new AestheticDialog.Builder(RegisterNext.this, DialogStyle.TOASTER, DialogType.ERROR)
                                       .setTitle("Error")
                                       .setMessage("Password Tidak Sama")
                                       .show();
                           }else{
                               postDaftar();
                           }
                       }
                   }else {
                       if(pngkt == null|| status == null || stn == 0 ||nost.getText().toString().isEmpty()||notr.getText().toString().isEmpty()||gaji.getText().toString().isEmpty()||npp.getText().toString().isEmpty() || pass.getText().toString().isEmpty()||pass2.getText().toString().isEmpty()){
                           new AestheticDialog.Builder(RegisterNext.this, DialogStyle.TOASTER,DialogType.ERROR)
                                   .setTitle("Error")
                                   .setMessage("Silahkan Semua di isi dengan baik")
                                   .setGravity(Gravity.CENTER)
                                   .show();
                       }else {
                           if(!pass.getText().toString().equals(pass2.getText().toString())) {
                               new AestheticDialog.Builder(RegisterNext.this, DialogStyle.TOASTER, DialogType.ERROR)
                                       .setTitle("Error")
                                       .setMessage("Password Tidak Sama")
                                       .show();
                           }else{
                               postDaftar();
                           }
                       }
                   }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Spinner spin = (Spinner)adapterView;
        Spinner spin2 = (Spinner)adapterView;
        if(spin.getId() == R.id.pngkt_gol)
        {
//            Toast.makeText(this, "Kamu Memilih : " + text,Toast.LENGTH_SHORT).show();
            pngkt = text;
        }
        if(spin2.getId() == R.id.stn_kerja)
        {
            stn = logic(text);

        }
    }

    private int logic(String text){
        String[] strings = {"s","Lanmar","Akun","BP Kwini","Den Ang" , "Den Har", "Den Sik", "Primkopal"};
        for(int i = 0 ; i <= strings.length;i++){
            if(strings[i].equals(text)){

                Toast.makeText(this, "Kamu Memilih : " + i,Toast.LENGTH_SHORT).show();
                return i;
            }
        }
        return 0;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
