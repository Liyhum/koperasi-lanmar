package com.example.koperasilanmar.screens.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.model.Results;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.Locale;

public class Done extends Fragment {

    private TextView notr,nost,tgl,judul,message,simpanans;
    private String mutpen,simpanan;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        notr = view.findViewById(R.id.TR);
        nost = view.findViewById(R.id.ST);
        tgl = view.findViewById(R.id.keluar_stn);
        judul = view.findViewById(R.id.judul);
        message = view.findViewById(R.id.message);
        simpanans = view.findViewById(R.id.simpanan);
        getPro();
        // Inflate the layout for this fragment
        return view;
    }
    private void  getPro(){
        Gson gson = new Gson();
        SharedPreferences preferences = getActivity().getSharedPreferences("pref_login", Context.MODE_PRIVATE);
        String json = preferences.getString("Profile", "");
        Results obj = gson.fromJson(json, Results.class);
        notr.setText(obj.getNoTr());
        nost.setText(obj.getNoSt());
        if(obj.getActiveMutasi().equals(1)){
            judul.setText("Mutasi");
            tgl.setText(obj.getTglmutasi());

        }else if (obj.getActivePensiun().equals(1)){
            judul.setText("Pensiun");
            tgl.setText(obj.getTglPensiun());
            message.setText("Silahkan ambil dana pensiun anda diKantor Primkopal Lanmar Jakarta\n" +
                    "tabungan anda sebesar");

        }

        simpanans.setText(formatRupiah(Double.parseDouble(obj.getSimpanan().toString())));


    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}