package com.example.koperasilanmar.screens.menu.adminAll;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
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
import com.example.koperasilanmar.utils.adapter.GroupAllAdapter;
import com.example.koperasilanmar.utils.adapter.PangkatAdapter;
import com.example.koperasilanmar.utils.model.admin.ListPangkat;
import com.example.koperasilanmar.utils.model.admin.Result;
import com.example.koperasilanmar.utils.model.expended.Grouped;
import com.example.koperasilanmar.utils.model.expended.Personil;
import com.example.koperasilanmar.utils.router.services.ServiceListGroup;
import com.example.koperasilanmar.utils.router.services.ServiceListPangkat;
import com.example.koperasilanmar.utils.router.services.ServiceUpdateGasimALL;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin extends Fragment {

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
    private MaterialButton materialButton;


    private ArrayList<Personil> modelLists;
    public Admin() {
        // Required empty public constructor
    }

    public static Admin newInstance(String param1, String param2) {
        Admin fragment = new Admin();
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
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        recyclerView = view.findViewById(R.id.list);
        recyclerView2 = view.findViewById(R.id.listGrouped);
        name = view.findViewById(R.id.name);
        materialButton = view.findViewById(R.id.verifikasi);
        results = new ArrayList<>();
        groupeds = new ArrayList<>();
        modelLists = new ArrayList<>();
        progressBar= view.findViewById(R.id.loading);
        getList();
        getListGrouped();
        cli();
        // Inflate the layout for this fragment
        return view;
    }

    private void cli(){
        ServiceUpdateGasimALL serviceUpdateGasimALL = new ServiceUpdateGasimALL();

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext()).setMessage("Apakah anda yakin ingin untuk Verifikasi?" )
                        .setPositiveButton("Iya" , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                serviceUpdateGasimALL.getModel(getActivity()).observe(getActivity(), new Observer<JsonObject>() {
                                    @Override
                                    public void onChanged(JsonObject jsonObject) {
                                        if(jsonObject.get("msg").toString().contains("Berhasil")){
                                            new AestheticDialog.Builder(getActivity(), DialogStyle.TOASTER, DialogType.SUCCESS)
                                                    .setTitle("Succes")
                                                    .setAnimation(DialogAnimation.SLIDE_UP)
                                                    .setGravity(Gravity.CENTER)
                                                    .setMessage(String.valueOf(jsonObject.get("msg")))
                                                    .show();
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void setAdapter(){
        pangkatAdapter = new PangkatAdapter(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pangkatAdapter);
    }

    private void setAdapter2(){
        groupAdapter = new GroupAllAdapter(getContext(),getActivity(), modelLists,"Admin");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(groupAdapter);
    }

    private void getList(){
        progressBar.setVisibility(View.VISIBLE);
        ServiceListPangkat serviceListPangkat = new ServiceListPangkat();
        serviceListPangkat.getModel().observe(getActivity(), new Observer<ListPangkat>() {
            @Override
            public void onChanged(ListPangkat listPangkat) {
                for (int i = 0; i<listPangkat.getResult().size(); i ++){
                    Result result = new Result();
                    result.setNamaStnKerja(listPangkat.getResult().get(i).getNamaStnKerja());
                    result.setNumber(listPangkat.getResult().get(i).getNumber());
                    results.add(result);
                }
                setAdapter();

                progressBar.setVisibility(View.INVISIBLE);
//                Toast.makeText(getActivity(), listPangkat.getResult().get(1).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListGrouped(){
        progressBar.setVisibility(View.VISIBLE);
        ServiceListGroup serviceListGroup = new ServiceListGroup();
        serviceListGroup.getModel("admin").observe(getActivity(), new Observer<Grouped>() {
            @Override
            public void onChanged(Grouped grouped) {
                for (Personil item : grouped.getPersonil()){
                    Personil per = new Personil();
                        per.setUsers(item.getUsers());
                        per.setStatus(item.getStatus());
                        per.setSimpanan(item.getSimpanan());
                        modelLists.add(per);
                }
                setAdapter2();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}