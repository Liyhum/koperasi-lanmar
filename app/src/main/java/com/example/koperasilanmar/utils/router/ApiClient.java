package com.example.koperasilanmar.utils.router;

import com.example.koperasilanmar.utils.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient mInstannce;
    private final Retrofit retrofit;
    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build();

    }
    public static synchronized ApiClient getInstance(){
        if(mInstannce==null){
            mInstannce=new ApiClient();
        }
        return mInstannce;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
