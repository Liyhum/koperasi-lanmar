package com.example.koperasilanmar.utils.router.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koperasilanmar.utils.model.users.ListUsers;
import com.example.koperasilanmar.utils.router.Api;
import com.example.koperasilanmar.utils.router.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceListUsers extends ViewModel{
    MutableLiveData<ListUsers> modelGroupingMutableLiveData;
    private String id;
    public MutableLiveData<ListUsers> getModel(String id){
        this.id = id;
        if (modelGroupingMutableLiveData == null){
            modelGroupingMutableLiveData =new MutableLiveData<>();
            setModel();
        }
        return modelGroupingMutableLiveData;
    }

    private void setModel() {
        Api api = ApiClient.getInstance().getApi();
        api.getListUser(id)
                .enqueue(new Callback<ListUsers>() {
                    @Override
                    public void onResponse(Call<ListUsers> call, Response<ListUsers> response) {
                        Log.d("Sukses", response.message());
                        if (response.isSuccessful()){
                            modelGroupingMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<ListUsers> call, Throwable t) {
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
