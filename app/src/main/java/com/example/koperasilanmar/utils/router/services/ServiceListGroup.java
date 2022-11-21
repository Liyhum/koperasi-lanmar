package com.example.koperasilanmar.utils.router.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koperasilanmar.utils.model.expended.Grouped;
import com.example.koperasilanmar.utils.router.Api;
import com.example.koperasilanmar.utils.router.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceListGroup extends ViewModel{
    MutableLiveData<Grouped> modelGroupingMutableLiveData;
    String id;
    public MutableLiveData<Grouped> getModel(String id){
        this.id=id;
        if (modelGroupingMutableLiveData == null){
            modelGroupingMutableLiveData =new MutableLiveData<>();
            setModel();
        }
        return modelGroupingMutableLiveData;
    }

    private void setModel() {
        Api api = ApiClient.getInstance().getApi();
        api.getGroup(id)
                .enqueue(new Callback<Grouped>() {
                    @Override
                    public void onResponse(Call<Grouped> call, Response<Grouped> response) {
                        Log.d("Sukses", response.message());
                        if (response.isSuccessful()){
                            modelGroupingMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<Grouped> call, Throwable t) {
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
