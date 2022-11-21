package com.example.koperasilanmar.utils.router;

import com.example.koperasilanmar.utils.model.expended.Grouped;
import com.example.koperasilanmar.utils.model.LoginModel;
import com.example.koperasilanmar.utils.model.admin.ListPangkat;
import com.example.koperasilanmar.utils.model.pensiun.ListPensiun;
import com.example.koperasilanmar.utils.model.users.ListUsers;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {
    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @POST("login")
    Call<LoginModel> login(@Body RequestBody jsonObject);

    @Headers({
        "Accept: application/json",
                "User-Agent: Lanmar Mobile",
                "Authorization: L4nm4rk0p3r4s1"
    })
    @PUT("update_true_mutasi")
    Call<JsonObject> updateMutasi(@Body RequestBody jsonObject);

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @PUT("update_true_pensiun")
    Call<JsonObject> updatePensiun(@Body RequestBody jsonObject);

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @POST("pensiun")
    Call<JsonObject> kirimPensiun(@Body RequestBody jsonObject);

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @GET("get_mutasi")
    Call<com.example.koperasilanmar.utils.model.mutasi_pensiun.ListPensiun> getMutasi();

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @GET("get_list_all/{id}")
    Call<ListUsers> getListUser(@Path("id") String id_stn);

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @GET("get_gasim/{id}")
    Call<JsonObject> get_gasim(@Path("id") int id_stn);

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @PUT("update_gasim/{id}")
    Call<JsonObject> updateGasim(@Path("id") int id_stn,@Body RequestBody total);

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @PUT("update_all_gasim")
    Call<JsonObject> updateAllGasim();
    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @GET("get_pensiun")
    Call<ListPensiun> getPensiun();

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @POST("mutasi")
    Call<JsonObject> kirimMutasi(@Body RequestBody jsonObject);
    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @GET("get_list")
    Call<ListPangkat> getList();

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @GET("get_personil_detail/{id}")
    Call<Grouped> getGroup(@Path("id") String id);

    @Headers({
            "Accept: application/json",
            "User-Agent: Lanmar Mobile",
            "Authorization: L4nm4rk0p3r4s1"
    })
    @Multipart
    @POST("register")
    Call<JsonObject> register(
            @Part("nama_lengkap") RequestBody nama,
            @Part("jenis_kelamin") RequestBody jk,
            @Part("tgl_lahir") RequestBody tgl,
            @Part("tmp_lahir") RequestBody tmp,
            @Part("no_tr") RequestBody notr,
            @Part("no_st") RequestBody nost,
            @Part("no_kt") RequestBody nokt,
            @Part("msk_satuan") RequestBody msk_st,
            @Part("npp") RequestBody npp,
            @Part("status") RequestBody status,
            @Part("pngkt_gol") RequestBody pngkt,
            @Part("nomor_hp") RequestBody nohp,
            @Part("email") RequestBody email,
            @Part("id_role") Integer id_role,
            @Part("gaji") RequestBody gaji,
            @Part("id_stn_kerja") Integer id_stn,
            @Part("simpanan") Integer simpanan,
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part MultipartBody.Part file);

}
