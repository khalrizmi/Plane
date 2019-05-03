package com.hamz.hamzplane.api;

import com.hamz.hamzplane.model.GetPenerbangan;
import com.hamz.hamzplane.model.GetRingkas;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Hamz on 16/12/2017.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("android/getTerbang")
    Call<GetPenerbangan> getPenerbangan(@Field("kdAsal") String kdAsal,
                                        @Field("kdTujuan") String kdTujuan,
                                        @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @POST("android/rowTerbang")
    Call<GetRingkas> getRowTerbang(@Field("id_penerbangan") Integer id_penerbangan);

    @FormUrlEncoded
    @POST("android/tambahPesan")
    Call<ResponseBody> simpanPesan(@Field("id_penerbangan") Integer id_penerbangan,
                                   @Field("nama") String nama,
                                   @Field("email") String email,
                                   @Field("telepon") String telepon,
                                   @Field("tiket_anak") Integer tiketAnak,
                                   @Field("tiket_dewasa") Integer tiketDewasa,
                                   @Field("total") Integer total);


}
