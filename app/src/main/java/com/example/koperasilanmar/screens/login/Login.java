package com.example.koperasilanmar.screens.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.ArrayMap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.screens.home.Home;
import com.example.koperasilanmar.screens.register.Register;
import com.example.koperasilanmar.utils.SessionManager;
import com.example.koperasilanmar.utils.model.LoginModel;
import com.example.koperasilanmar.utils.router.services.ServiceLoginModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;

public class Login extends AppCompatActivity {
    private EditText user,pass;
    private TextView forget,register;
    private boolean passwordShown;
    private Button button;
    private SessionManager sessionManager;
    SharedPreferences mPrefs ;
    SharedPreferences.Editor prefsEditor;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        forget = findViewById(R.id.forgot);
        register = findViewById(R.id.register);
        button = findViewById(R.id.masuk);
        button.setClickable(false);

        mPrefs = getSharedPreferences("pref_login",MODE_PRIVATE);
        prefsEditor =  mPrefs.edit();

        sessionManager = new SessionManager(getApplicationContext());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ForgetPassword.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
        pass.setOnTouchListener(new View.OnTouchListener() {
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
    }
    private void onLogin(){
        button.setClickable(false);
        button.setText("Loading.....");
        ServiceLoginModel serviceLoginModel = new ServiceLoginModel();
        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("username", user.getText().toString().trim());
        jsonParams.put("password", pass.getText().toString().trim());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
        serviceLoginModel.getModel(body,button,Login.this).observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                if(loginModel.getMsg().contains("Berhasil")){
                    button.setClickable(true);
                    button.setText("Masuk");
//                    save profile
                    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

                    String json = gson.toJson(loginModel.getResults());
                    Log.d("Canda",json);
                    prefsEditor.putString("Profile", json);
                    prefsEditor.commit();
                    Log.d("PROFILETOD",mPrefs.getString("ISENG",""));
                    sessionManager.createSession(loginModel.getToken(),loginModel.getResults().getNamaRole(),loginModel.getResults().getIdUsers());
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                    startActivity(i);
                }else{
                    button.setClickable(true);
                    button.setText("Masuk");
                    new AestheticDialog.Builder(Login.this, DialogStyle.EMOTION, DialogType.ERROR)
                            .setTitle("Error")
                            .setAnimation(DialogAnimation.SLIDE_UP)
                            .setMessage(loginModel.getMsg())
                            .show();
                }
            }
        });
    }
}
