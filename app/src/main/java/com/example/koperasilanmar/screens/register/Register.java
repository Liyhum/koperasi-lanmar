package com.example.koperasilanmar.screens.register;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.koperasilanmar.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Register extends AppCompatActivity {
    private static final int PHOTO_PICKER_REQUEST_CODE =1 ;
    Button button;
    ImageView cam,gallery,back;
    TextView tglLahir;
    EditText nama,tmpt_lahir;
    String jk,imgPath;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);
        button = findViewById(R.id.lanjut);
        tglLahir = findViewById(R.id.set_date);
        nama = findViewById(R.id.nama_lengkap);
        cam = findViewById(R.id.kamera);
        gallery =findViewById(R.id.photo);
        tmpt_lahir = findViewById(R.id.tmp_tinggal);
        myCalendar = Calendar.getInstance();
        back = findViewById(R.id.back);

        setButtonClick();

    }
    private void setButtonClick(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        tglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Register.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCam();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGallery();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterNext.class);
                intent.putExtra("name",nama.getText().toString());
                intent.putExtra("jk",jk);
                intent.putExtra("tgl_lahir",tglLahir.getText().toString());
                intent.putExtra("alamat",tmpt_lahir.getText().toString());
                intent.putExtra("img",imgPath == null ? "null" : imgPath);
                if(nama.getText().toString().isEmpty() || tmpt_lahir.getText().toString().isEmpty() || jk.isEmpty() || tglLahir.getText().toString().isEmpty()){
                    new AestheticDialog.Builder(Register.this, DialogStyle.TOASTER,DialogType.ERROR)
                            .setTitle("Error")
                            .setMessage("Silahkan Semua di isi dengan baik")
                            .setAnimation(DialogAnimation.SHRINK)
                            .show();
                }else {
                    startActivity(intent);
                }
            }
        });
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_jk);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                jk = checkedRadioButton.getText().toString();
            }
        });
    }
    private void updateLabel(){
        String myFormat="yyyy/MM/dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        dateFormat.setTimeZone(tz);
        tglLahir.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void getGallery() {
        ImagePicker.with(this)
                .galleryOnly()
                .crop()
                .start();

    }

    private void getCam(){
        ImagePicker.with(this)
                .cameraOnly()
                .crop()
                .start();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            Uri currentUri = data.getData();
            imgPath = currentUri.getPath();
            Toast.makeText(this, "Berhasil Ambil gambar", Toast.LENGTH_SHORT).show();

            return;
        }else{
            Toast.makeText(this, "Cancel Pick Photo", Toast.LENGTH_SHORT).show();
        }
    }
}
