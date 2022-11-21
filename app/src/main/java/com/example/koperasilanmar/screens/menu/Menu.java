package com.example.koperasilanmar.screens.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.Constants;
import com.example.koperasilanmar.utils.model.Results;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private TextView gaji,simpanan,nama,npp,status,total;
    String msk_stn;
    private ImageView imageView;
    private String pngktGol;
    ListView simpleList;
    public Menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
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
        View view =inflater.inflate(R.layout.fragment_menu, container, false);
        nama = view.findViewById(R.id.name);
        npp = view.findViewById(R.id.npp);
        status = view.findViewById(R.id.status);
        imageView = view.findViewById(R.id.avatar);
        total = view.findViewById(R.id.total);
        simpleList = view.findViewById(R.id.simpleListView);
        getProfile();

        // Inflate the layout for this fragment
        return view;
    }

    private void getProfile(){
        Gson gson = new Gson();
        SharedPreferences preferences = getActivity().getSharedPreferences("pref_login", Context.MODE_PRIVATE);
        String json = preferences.getString("Profile", "");
        Results obj = gson.fromJson(json, Results.class);
        msk_stn = obj.getMskSatuan();
        nama.setText(obj.getNamaLengkap());
        npp.setText(obj.getNpp());
        status.setText(obj.getStatus());
        pngktGol = obj.getPngktGol();
        getRincian();
        String url = Constants.base_url + "image/" + obj.getImgUser();
        Log.d("tod", "getProfile: "+url);
        Glide.with(this).load(url).into(imageView);
    }

    private void getRincian(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat curFormater = new SimpleDateFormat("yyyy/MM/dd");
        Date startDate = null;
        try {
            startDate = curFormater.parse(msk_stn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = new Date();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        int simp = pngktGol.equals("Perwira") ? status.equals("Honorer")? 25000:100000 : pngktGol.equals("Gol") ? 25000:50000;
        int totals = simp * diffMonth;
        total.setText(formatRupiah(Double.parseDouble(String.valueOf(totals))));
        Log.d("TAG", "getRincian: " + diffMonth + endDate);
        List<String> date_string =new ArrayList<>();
        for (Date date = startCalendar.getTime(); startCalendar.before(endCalendar); startCalendar.add(Calendar.MONTH, 1), date = startCalendar.getTime()) {
            date_string.add(curFormater.format(date) + "                         " + formatRupiah(Double.parseDouble(String.valueOf(simp))));
        }
        date_string.remove(date_string.size() -1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_view_user, R.id.textView, date_string);
        simpleList.setAdapter(arrayAdapter);

    }
    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}