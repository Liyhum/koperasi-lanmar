package com.example.koperasilanmar.utils.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.koperasilanmar.R;
import com.example.koperasilanmar.utils.Constants;
import com.example.koperasilanmar.utils.model.users.ResultUser;
import com.example.koperasilanmar.utils.router.services.ServiceUpdateGasim;
import com.google.gson.JsonObject;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.RequestBody;

public class GroupListUserAdapter extends RecyclerView.Adapter<GroupListUserAdapter.MyView> {

    private ArrayList<ResultUser> modelLists;
    private LifecycleOwner adminAll;
    private Activity activity;

    public GroupListUserAdapter(Activity activity,LifecycleOwner adminAll,ArrayList<ResultUser> modelLists){
        this.adminAll = adminAll;
        this.activity = activity;
        this.modelLists = modelLists;
    }


    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private TextView npp;
        private TextView status;
        private ImageView imageView,show;
        public MyView(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name);
            npp = itemView.findViewById(R.id.npp);
            status = itemView.findViewById(R.id.status);
            imageView = itemView.findViewById(R.id.avatar);
            show = itemView.findViewById(R.id.show);

        }
        @Override
        public void onClick(View view) {

        }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent,false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        int id_users;
        id_users = modelLists.get(position).getIdUsers();
        String dateStr,status,pngktGol;
        dateStr = modelLists.get(position).getMskSatuan();
        status = modelLists.get(position).getStatus();
        pngktGol = modelLists.get(position).getPngktGol();
        holder.name.setText(modelLists.get(position).getNamaLengkap());
        holder.npp.setText(modelLists.get(position).getNpp());
        holder.status.setText(modelLists.get(position).getStatus());
        String url = Constants.base_url + "image/" + modelLists.get(position).getImgUser();
        Glide.with(activity).load(Uri.parse(url)).into(holder.imageView);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat curFormater = new SimpleDateFormat("yyyy/MM/dd");
        Date startDate = null;
        try {
            startDate = curFormater.parse(dateStr);
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
        int total = simp * diffMonth;
        List<String> date_string =new ArrayList<>();
        for (Date date = startCalendar.getTime(); startCalendar.before(endCalendar); startCalendar.add(Calendar.MONTH, 1), date = startCalendar.getTime()) {
            date_string.add(curFormater.format(date) + "    " + formatRupiah(Double.parseDouble(String.valueOf(simp))));
        }
        date_string.remove(date_string.size() -1);
        date_string.add("Total Simpanan        " + formatRupiah(Double.parseDouble(String.valueOf(total))));
        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm(date_string,simp);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceUpdateGasim serviceUpdateGasim = new ServiceUpdateGasim();
                Map<String, Object> jsonParams = new ArrayMap<>();
                jsonParams.put("total_simpanan", total);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
                new AlertDialog.Builder(activity).setMessage("Apakah anda yakin ingin untuk Verifikasi?" )
                        .setPositiveButton("Iya" , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                serviceUpdateGasim.getModel(body,id_users, activity).observe(adminAll, new Observer<JsonObject>() {
                                    @Override
                                    public void onChanged(JsonObject jsonObject) {
                                        new AestheticDialog.Builder(activity, DialogStyle.TOASTER, DialogType.SUCCESS)
                                                .setTitle("Succes")
                                                .setAnimation(DialogAnimation.SLIDE_UP)
                                                .setMessage(String.valueOf(jsonObject.get("msg")))
                                                .show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                activity.finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void DialogForm(List<String> date_string, int simp ) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat curFormater = new SimpleDateFormat("yyyy / MMM / dd");
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        LayoutInflater  inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_rincian, null);
        ListView simpleList = (ListView) dialogView.findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, R.layout.row_view_user, R.id.textView, date_string);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Rincian Simpanan");
        Log.d("Tag",date_string.toString());
        simpleList.setAdapter(arrayAdapter);
        dialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

    @Override
    public int getItemCount() {
        if(modelLists != null){
            return modelLists.size();
        }
        return 0;
    }

}
