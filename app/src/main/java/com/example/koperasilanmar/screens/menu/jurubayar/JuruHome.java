package com.example.koperasilanmar.screens.menu.jurubayar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.model.expended.Grouped;
import com.example.koperasilanmar.utils.model.expended.Personil;
import com.example.koperasilanmar.utils.adapter.GroupAllAdapter;
import com.example.koperasilanmar.utils.adapter.PangkatAdapter;
import com.example.koperasilanmar.utils.model.Results;
import com.example.koperasilanmar.utils.model.admin.Result;
import com.example.koperasilanmar.utils.router.services.ServiceListGroup;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

public class JuruHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ProgressBar progressBar;
    private RecyclerView recyclerView,recyclerView2;
    private PangkatAdapter pangkatAdapter;
    private GroupAllAdapter groupAdapter;
    private TextView name;
    private ArrayList<Result> results;
    private ArrayList<Grouped> groupeds;
    Results result;
    private ArrayList<Personil> modelLists;
    public JuruHome() {
        // Required empty public constructor
    }

    public static JuruHome newInstance(String param1, String param2) {
        JuruHome fragment = new JuruHome();
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
        View view = inflater.inflate(R.layout.fragment_juru_bayar, container, false);
        recyclerView2 = view.findViewById(R.id.listGrouped);
        name = view.findViewById(R.id.name);
        results = new ArrayList<>();
        groupeds = new ArrayList<>();
        modelLists = new ArrayList<>();
        progressBar= view.findViewById(R.id.loading);
        Gson gson = new Gson();
        SharedPreferences preferences = getActivity().getSharedPreferences("pref_login", Context.MODE_PRIVATE);
        String json = preferences.getString("Profile", "");
        result = gson.fromJson(json, Results.class);
        getListGrouped();
        // Inflate the layout for this fragment
        return view;
    }
    private void setAdapter2(){
        groupAdapter = new GroupAllAdapter(getContext(), getActivity(),modelLists,"Juru");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(groupAdapter);
    }
    private void getListGrouped(){
        progressBar.setVisibility(View.VISIBLE);
        ServiceListGroup serviceListGroup = new ServiceListGroup();
        serviceListGroup.getModel("active").observe(getActivity(), new Observer<Grouped>() {
            @Override
            public void onChanged(Grouped grouped) {

                for (Personil item : grouped.getPersonil()){
                    Personil per = new Personil();
                    per.setUsers(item.getUsers());
                    per.setStatus(item.getStatus());
                    per.setSimpanan(item.getSimpanan());
                    if(result.getNamaLengkap().toLowerCase(Locale.ROOT).contains(item.getStatus().toLowerCase(Locale.ROOT))){
                        modelLists.add(per);
                    }
                }
                setAdapter2();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}