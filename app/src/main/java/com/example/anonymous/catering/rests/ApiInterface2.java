package com.example.anonymous.catering.rests;

import com.example.anonymous.catering.response.ResponGuruDetail;
import com.example.anonymous.catering.response.ResponseCreateMurid;
import com.example.anonymous.catering.response.ResponseCreatePemesanan;
import com.example.anonymous.catering.response.ResponseGambar;
import com.example.anonymous.catering.response.ResponseGuru;
import com.example.anonymous.catering.response.ResponseLoginMember;
import com.example.anonymous.catering.response.ResponseMatpel;
import com.example.anonymous.catering.response.ResponsePaket;
import com.example.anonymous.catering.response.ResponsePaketById;
import com.example.anonymous.catering.response.ResponsePembayaran;
import com.example.anonymous.catering.response.ResponsePemesanan;
import com.example.anonymous.catering.response.ResponsePemesananJoin;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface2 {
    @GET("pemesan/find-by-member")
    Call<ResponsePemesanan> pemesananFindByMember(@Query("id") String id);

    @GET("pembayaran/find-by-pemesanan")
    Call<ResponsePembayaran> pembayaranFindByPemesanan(@Query("id") String id);

    @GET("pemesan/find")
    Call<ResponsePemesananJoin> pemesananJoin(@Query("id") String id);

    @Multipart
    @POST("upload/uploadimage.php")
    Call<ResponseGambar> uploadImage(@Part MultipartBody.Part image,
                                     @Part("pemesanan_id") RequestBody pemesanan,
                                     @Part("jumlah_transfer") RequestBody jumlah_transfer,
                                     @Part("total") RequestBody total);
}
