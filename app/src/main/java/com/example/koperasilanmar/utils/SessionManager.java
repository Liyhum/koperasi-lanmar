package com.example.koperasilanmar.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.koperasilanmar.screens.home.Home;
import com.example.koperasilanmar.screens.login.Login;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "crudpref";
    private static final String is_login = "islogin";
    public static final String token = "token";
    public static final String level = "level";
    public static final String emails = "id_users";


    public SessionManager(Context context) {

        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    private boolean is_login() {
        return pref.getBoolean(is_login, false);
    }

    public void checkLogin(){
        if (this.is_login()){
            Intent i = new Intent(context, Home.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            Intent i = new Intent(context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public void createSession(String email,String levels,int id_users){
        editor.putBoolean(is_login, true);
        editor.putString(token, email);
        editor.putString(level, levels);
        editor.putInt(emails,id_users);
        editor.commit();
    }


    public void logout(){
        editor.clear();
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(token, pref.getString(token, null));
        return user;
    }

}

