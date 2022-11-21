package com.example.koperasilanmar.utils.router.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koperasilanmar.utils.model.ListMutasi;
import com.example.koperasilanmar.utils.model.mutasi_pensiun.ListPensiun;
import com.example.koperasilanmar.utils.router.Api;
import com.example.koperasilanmar.utils.router.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceListMutasi extends ViewModel{
    MutableLiveData<ListPensiun> modelGroupingMutableLiveData;
    public MutableLiveData<ListPensiun> getModel(){
        if (modelGroupingMutableLiveData == null){
            modelGroupingMutableLiveData =new MutableLiveData<>();
            setModel();
        }
        return modelGroupingMutableLiveData;
    }

    private void setModel() {
        Api api = ApiClient.getInstance().getApi();
        api.getMutasi()
                .enqueue(new Callback<ListPensiun>() {
                    @Override
                    public void onResponse(Call<ListPensiun> call, Response<ListPensiun> response) {
                        Log.d("Sukses", response.body().getMsg());
                        if (response.isSuccessful()){
                            modelGroupingMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<ListPensiun> call, Throwable t) {
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
