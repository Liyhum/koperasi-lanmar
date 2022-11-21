package com.example.koperasilanmar.utils.router.services;

import android.util.Log;
import android.widget.Button;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koperasilanmar.screens.login.Login;
import com.example.koperasilanmar.utils.model.LoginModel;
import com.example.koperasilanmar.utils.router.Api;
import com.example.koperasilanmar.utils.router.ApiClient;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceLoginModel extends ViewModel{
    MutableLiveData<LoginModel> modelGroupingMutableLiveData;
    private RequestBody id;
    private Login cons;
    Button button;
    public MutableLiveData<LoginModel> getModel(RequestBody id, Button button, Login context){
        this.id = id;
        this.cons = context;
        this.button = button;
        if (modelGroupingMutableLiveData == null){
            modelGroupingMutableLiveData =new MutableLiveData<>();
            setModel();
        }
        return modelGroupingMutableLiveData;
    }

    private void setModel() {
        Api api = ApiClient.getInstance().getApi();
        api.login(id)
                .enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        Log.d("Sukses", response.message());
                        if (response.isSuccessful()){
                            modelGroupingMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        new AestheticDialog.Builder(cons, DialogStyle.CONNECTIFY, DialogType.ERROR)
                                .setTitle("Error")
                                .setAnimation(DialogAnimation.SLIDE_UP)
                                .setMessage(t.getMessage())
                                .show();
                        button.setClickable(true);
                        button.setText("Masuk");
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
