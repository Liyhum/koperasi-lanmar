package com.example.koperasilanmar.screens.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.koperasilanmar.MainActivity;
import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.Constants;
import com.example.koperasilanmar.utils.SessionManager;
import com.example.koperasilanmar.utils.model.Results;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MaterialButton materialButton;

    private TextView gaji,simpanan,nama,npp,status,total;
    private ImageView imageView;

    public Profile() {
        // Required empty public constructor
    }

    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        materialButton = view.findViewById(R.id.logout);
        SessionManager sessionManager = new SessionManager(view.getContext());
        nama = view.findViewById(R.id.name);
        npp = view.findViewById(R.id.npp);
        status = view.findViewById(R.id.status);
        imageView = view.findViewById(R.id.avatar);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                getActivity().finish();
            }
        });
        getProfile();
        // Inflate the layout for this fragment
        return view;
    }
    private void getProfile(){
        Gson gson = new Gson();
        SharedPreferences preferences = getActivity().getSharedPreferences("pref_login", Context.MODE_PRIVATE);
        String json = preferences.getString("Profile", "");
        Results obj = gson.fromJson(json, Results.class);
        nama.setText(obj.getNamaLengkap());
        npp.setText(obj.getNpp());
        status.setText(obj.getStatus());
        String url = Constants.base_url + "image/" + obj.getImgUser();
        Log.d("tod", "getProfile: "+url);
        Glide.with(this).load(url).into(imageView);


    }
}