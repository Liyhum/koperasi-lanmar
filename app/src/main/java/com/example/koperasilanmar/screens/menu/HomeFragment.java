package com.example.koperasilanmar.screens.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.koperasilanmar.R;
import com.example.koperasilanmar.screens.mutasi.MutasiKirim;
import com.example.koperasilanmar.screens.mutasi.PensiunKirim;
import com.example.koperasilanmar.utils.Constants;
import com.example.koperasilanmar.utils.model.Results;
import com.example.koperasilanmar.utils.router.services.ServiceListGasim;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ProgressBar progressBar;
    private TextView gaji,simpanan,nama,npp,status,total;
    private ImageView imageView;
    private MaterialButton mutasi,pensiunan;
    String notr;
    String nost;
    Integer id_user;
    private String pngktGol,msk_stn;

    public HomeFragment() {

    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = view.findViewById(R.id.loading);
        progressBar.setVisibility(View.INVISIBLE);
        mutasi = view.findViewById(R.id.mutasi);
        pensiunan = view.findViewById(R.id.pensiun);
        gaji = view.findViewById(R.id.gaji_bersih);
        simpanan = view.findViewById(R.id.simpanan);
        nama = view.findViewById(R.id.name);
        npp = view.findViewById(R.id.npp);
        status = view.findViewById(R.id.status);
        total = view.findViewById(R.id.total);
        imageView = view.findViewById(R.id.avatar);
        getProfile();
        mutasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutasiKirim.class);

                intent.putExtra("id",id_user);
                intent.putExtra("no_tr",notr);
                intent.putExtra("no_st",nost);
                getContext().startActivity(intent);
            }
        });
        pensiunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PensiunKirim.class);

                intent.putExtra("id",id_user);
                intent.putExtra("no_tr",notr);
                intent.putExtra("no_st",nost);
                getContext().startActivity(intent);
            }
        });
        return view;
    }

    private void getgasim(int id){
        ServiceListGasim serviceListGasim = new ServiceListGasim();
        serviceListGasim.getModel(id).observe(getActivity(), new Observer<JsonObject>() {
            @Override
            public void onChanged(JsonObject jsonObject) {
                Log.d("Tag", jsonObject.get("msg").toString().contains("Data") ? "TOD" : "GA");

                if(jsonObject.get("msg").toString().contains("Data")){
//                    Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                    simpanan.setText(formatRupiah(0.0));
                    total.setText(formatRupiah(0.0));
                }else {
                    try {
                            JSONObject jsonObject1 = new JSONObject(String.valueOf(jsonObject.get("data")));
                            Log.d("TAG", jsonObject1.toString());
                        int simp = pngktGol.equals("Perwira") ? status.equals("Honorer")? 25000:100000 : pngktGol.equals("Gol") ? 25000:50000;
                        simpanan.setText(formatRupiah(Double.parseDouble(String.valueOf(simp))));
                            total.setText(formatRupiah(Double.parseDouble(jsonObject1.getString("simpanan"))));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void getProfile(){
        progressBar.setVisibility(View.VISIBLE);
        Gson gson = new Gson();
        SharedPreferences preferences = getActivity().getSharedPreferences("pref_login",Context.MODE_PRIVATE);
        String json = preferences.getString("Profile", "");
        Results obj = gson.fromJson(json, Results.class);
        nost = obj.getNoSt();
        notr = obj.getNoTr();
        id_user = obj.getIdUsers();
        pngktGol = obj.getPngktGol();
        msk_stn = obj.getMskSatuan();
        getgasim(obj.getIdUsers());
//        simpanan.setText(formatRupiah(Double.parseDouble(String.valueOf(obj.getSimpanan()))));
        nama.setText(obj.getNamaLengkap());
        npp.setText(obj.getNpp());
        status.setText(obj.getStatus());
        gaji.setText(formatRupiah(Double.parseDouble(String.valueOf(obj.getGaji()))));
//        total.setText(formatRupiah(Double.parseDouble(String.valueOf(obj.getSimpanan()))));
        if(obj.getStatus().equals("Honorer")){
            mutasi.setVisibility(View.GONE);
            pensiunan.setVisibility(View.GONE);
        }
        String url = Constants.base_url + "image/" + obj.getImgUser();
        Log.d("tod", "getProfile: "+url);
        Glide.with(this).load(url).into(imageView);
        progressBar.setVisibility(View.INVISIBLE);

    }
    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}