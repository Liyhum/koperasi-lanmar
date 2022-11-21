package com.example.koperasilanmar.utils.router.services;

import android.util.Log;
import android.view.Gravity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koperasilanmar.screens.register.RegisterNext;
import com.example.koperasilanmar.utils.router.Api;
import com.example.koperasilanmar.utils.router.ApiClient;
import com.google.gson.JsonObject;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRegisterModel extends ViewModel{
    MutableLiveData<JsonObject> modelGroupingMutableLiveData;
    private RequestBody nama,jk,tgl,tmp,notr,nost,nokt,msk_st,status,pngkt,nohp,email,username,password,gaji,npp;
    private MultipartBody.Part img;
     Integer id_role,id_stn,simpanan;
    private RegisterNext cons;
    public MutableLiveData<JsonObject> getModel(RegisterNext context,RequestBody nama,RequestBody jk,RequestBody tgl,RequestBody tmp,RequestBody notr,RequestBody nost,RequestBody nokt,RequestBody msk_st,RequestBody status,RequestBody pngkt,RequestBody nohp,RequestBody email,Integer id_role,RequestBody gaji,Integer id_stn,Integer simpanan,RequestBody username,RequestBody password,Part image,RequestBody npp){
        this.nama = nama;
        this.jk = jk;
        this.tgl =tgl;
        this.tmp = tmp;
        this.npp = npp;
        this.notr = notr;
        this.nost = nost;
        this.nokt = nokt;
        this.msk_st = msk_st;
        this.status= status;
        this.pngkt = pngkt;
        this.nohp = nohp;
        this.email = email;
        this.id_role= id_role;
        this.gaji = gaji;
        this.id_stn = id_stn;
        this.simpanan = simpanan;
        this.username = username;
        this.password = password;

        this.cons = context;
        this.img = image;
        if (modelGroupingMutableLiveData == null){
            modelGroupingMutableLiveData =new MutableLiveData<>();
            setModel();
        }
        return modelGroupingMutableLiveData;
    }

    private void setModel() {
        Api api = ApiClient.getInstance().getApi();
        api.register(nama,jk,tgl,tmp,notr,nost,nokt,msk_st,npp,status,pngkt,nohp,email,id_role,gaji,id_stn,simpanan,username,password,img)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d("Sukses", response.message());
                        if (response.isSuccessful()){
                            modelGroupingMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        new AestheticDialog.Builder(cons, DialogStyle.CONNECTIFY, DialogType.ERROR)
                                .setTitle("FAILURE")
                                .setAnimation(DialogAnimation.SLIDE_UP)
                                .setGravity(Gravity.CENTER)
                                .setMessage(t.getMessage())
                                .show();
                        Log.d("FAILURE", t.getMessage());
                    }
                });
    }
    public void onRefresh(){
        if(modelGroupingMutableLiveData.getValue() != null){
            setModel();
        }

    }
}
