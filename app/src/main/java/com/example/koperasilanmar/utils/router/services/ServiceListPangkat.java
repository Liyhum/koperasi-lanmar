package com.example.koperasilanmar.utils.router.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koperasilanmar.utils.model.admin.ListPangkat;
import com.example.koperasilanmar.utils.router.Api;
import com.example.koperasilanmar.utils.router.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceListPangkat extends ViewModel{
    MutableLiveData<ListPangkat> modelGroupingMutableLiveData;
    public MutableLiveData<ListPangkat> getModel(){
        if (modelGroupingMutableLiveData == null){
            modelGroupingMutableLiveData =new MutableLiveData<>();
            setModel();
        }
        return modelGroupingMutableLiveData;
    }

    private void setModel() {
        Api api = ApiClient.getInstance().getApi();
        api.getList()
                .enqueue(new Callback<ListPangkat>() {
                    @Override
                    public void onResponse(Call<ListPangkat> call, Response<ListPangkat> response) {
                        Log.d("Sukses", response.body().getMsg());
                        if (response.isSuccessful()){
                            modelGroupingMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<ListPangkat> call, Throwable t) {
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
