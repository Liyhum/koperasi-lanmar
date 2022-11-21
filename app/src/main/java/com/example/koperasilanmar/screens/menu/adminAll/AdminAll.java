package com.example.koperasilanmar.screens.menu.adminAll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.adapter.GroupMenuAdapter;
import com.example.koperasilanmar.utils.adapter.GroupPenMenuAdapter;
import com.example.koperasilanmar.utils.model.mutasi_pensiun.ListPensiun;
import com.example.koperasilanmar.utils.model.mutasi_pensiun.Mutasi;
import com.example.koperasilanmar.utils.model.mutasi_pensiun.Pensiun;
import com.example.koperasilanmar.utils.router.services.ServiceListMutasi;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminAll#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminAll extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView,recyclerView2;
    private ArrayList<Mutasi> modelLists;
    private ArrayList<Pensiun> modelPensiun;
    private ProgressBar progressBar;


    public AdminAll() {
        // Required empty public constructor
    }
    public static AdminAll newInstance(String param1, String param2) {
        AdminAll fragment = new AdminAll();
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

        View view = inflater.inflate(R.layout.fragment_admin_all, container, false);
        recyclerView = view.findViewById(R.id.list_user);
        recyclerView2 = view.findViewById(R.id.list_pensiun);
        modelLists = new ArrayList<>();
        modelPensiun = new ArrayList<>();
        progressBar = view.findViewById(R.id.loading);

        getMutasi();
        return view;
    }
    private void getAdapter(){
        GroupMenuAdapter groupMenuAdapter =new GroupMenuAdapter(getActivity(),modelLists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(groupMenuAdapter);
    }

    private void getAdapter2(){
        GroupPenMenuAdapter groupPenMenuAdapter =new GroupPenMenuAdapter(getActivity(),modelPensiun);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(groupPenMenuAdapter);
    }

    private void  getMutasi(){
        progressBar.setVisibility(View.VISIBLE);
        ServiceListMutasi serviceListMutasi = new ServiceListMutasi();
        serviceListMutasi.getModel().observe(getActivity(), new Observer<ListPensiun>() {
            @Override
            public void onChanged(ListPensiun listMutasi) {
                if(listMutasi.getMutasi().isEmpty()){
                    Toast.makeText(getActivity(), "Maaf Data Kosong", Toast.LENGTH_SHORT).show();
                }else if(listMutasi.getPensiun().isEmpty()){
                    Toast.makeText(getActivity(), "Maaf Data Kosong", Toast.LENGTH_SHORT).show();
                }
                for (Mutasi item : listMutasi.getMutasi()){
                    Mutasi result = new Mutasi();
                    result = item;
                    modelLists.add(result);
                }
                for (Pensiun item: listMutasi.getPensiun()){
                    Pensiun pensiun = new Pensiun();
                    pensiun = item;
                    modelPensiun.add(pensiun);
                }
                progressBar.setVisibility(View.INVISIBLE);
                getAdapter();
                getAdapter2();
            }

        });
    }
}