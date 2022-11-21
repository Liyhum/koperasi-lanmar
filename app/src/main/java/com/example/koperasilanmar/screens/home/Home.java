package com.example.koperasilanmar.screens.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.koperasilanmar.R;
import com.example.koperasilanmar.screens.menu.HomeFragment;
import com.example.koperasilanmar.screens.menu.Menu;
import com.example.koperasilanmar.screens.menu.Profile;
import com.example.koperasilanmar.screens.menu.adminAll.Admin;
import com.example.koperasilanmar.screens.menu.adminAll.AdminAll;
import com.example.koperasilanmar.screens.menu.jurubayar.JuruHome;
import com.example.koperasilanmar.screens.menu.jurubayar.JuruMenu;
import com.example.koperasilanmar.utils.SessionManager;
import com.example.koperasilanmar.utils.model.Results;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Home extends AppCompatActivity {
    SessionManager sessionManager;

    MeowBottomNavigation meowBottomNavigation;
    private SharedPreferences mPrefs;
    Results obj;

    @Override
    protected void onCreate(Bundle savedIncState){
        super.onCreate(savedIncState);
        setContentView(R.layout.layout_user);

        mPrefs = getSharedPreferences("pref_login",MODE_PRIVATE);

        getProfile();
        String role = obj.getNamaRole();

        sessionManager = new SessionManager(getApplicationContext());
        meowBottomNavigation = findViewById(R.id.bottom_nav);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.balance_wallet));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.person));
        meowBottomNavigation.setOnShowListener(model -> {
            Fragment fragment = null;
            switch (model.getId()){
                case 1 :
                    fragment = logic();
                    break;
                case 2 :
                    fragment = logic2();
                    break;
                case 3:
                    fragment = new Profile();
            }
            loadFragment(fragment);
            return null;
        });
        meowBottomNavigation.show(1,true);

    }

    private Fragment logic(){
        if (obj.getNamaRole().equals("Juru Bayar")) {
            return new JuruHome();
        } else if (obj.getNamaRole().equals("Admin")) {
            return new Admin();
        }else {
            if (obj.getNamaRole().equals("User")) {
                assert obj.getActiveMutasi() != null;
                if(obj.getActiveMutasi().equals(1)){
                    return new Done();
                }else if (obj.getActivePensiun().equals(1)) {
                    return new Done();
                }
                    return new HomeFragment();
            }
        }
        return new HomeFragment();
    }
    private Fragment logic2(){
       if (obj.getNamaRole().equals("Juru Bayar")) {
            return new JuruMenu();
        } else if (obj.getNamaRole().equals("Admin")) {
            return new AdminAll();
        }else {
            if (obj.getNamaRole().equals("User")) {
                if(obj.getActiveMutasi().equals(1)){
                    return new Done();
                }else if (obj.getActivePensiun().equals(1)) {
                    return new Done();
                }
                    return new Menu();

            }
        }

        return new Menu();
    }
    private void getProfile(){
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        SharedPreferences preferences = getSharedPreferences("pref_login", Context.MODE_PRIVATE);
        String json = preferences.getString("Profile", "");
        Log.d("TODODO", "getProfile: " +json);
        obj = gson.fromJson(json, Results.class);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}
