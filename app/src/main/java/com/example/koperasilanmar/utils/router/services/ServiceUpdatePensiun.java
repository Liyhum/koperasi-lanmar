package com.example.koperasilanmar.utils.router.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koperasilanmar.utils.router.Api;
import com.example.koperasilanmar.utils.router.ApiClient;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceUpdatePensiun extends ViewModel{
    MutableLiveData<JsonObject> modelGroupingMutableLiveData;
    private RequestBody id;
    public MutableLiveData<JsonObject> getModel(RequestBody id){
        this.id = id;
        if (modelGroupingMutableLiveData == null){
            modelGroupingMutableLiveData =new MutableLiveData<>();
            setModel();
        }
        return modelGroupingMutableLiveData;
    }

    private void setModel() {
        Api api = ApiClient.getInstance().getApi();
        api.updatePensiun(id)
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
