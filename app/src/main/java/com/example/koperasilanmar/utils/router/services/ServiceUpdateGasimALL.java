package com.example.koperasilanmar.utils.router.services;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koperasilanmar.utils.router.Api;
import com.example.koperasilanmar.utils.router.ApiClient;
import com.google.gson.JsonObject;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceUpdateGasimALL extends ViewModel{
    MutableLiveData<JsonObject> modelGroupingMutableLiveData;
    private Activity cons;
    public MutableLiveData<JsonObject> getModel( Activity context){
        this.cons = context;
        if (modelGroupingMutableLiveData == null){
            modelGroupingMutableLiveData =new MutableLiveData<>();
            setModel();
        }
        return modelGroupingMutableLiveData;
    }

    private void setModel() {
        Api api = ApiClient.getInstance().getApi();
        api.updateAllGasim()
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
                                .setTitle("Error")
                                .setAnimation(DialogAnimation.SLIDE_UP)
                                .setMessage(t.getMessage())
                                .show();
                    }
                });
    }
    public void onRefresh(){
        if(modelGroupingMutableLiveData.getValue() != null){
            setModel();
        }

    }
}
