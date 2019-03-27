package com.example.anonymous.catering.rests;

import com.example.anonymous.catering.response.ResponGuruDetail;
import com.example.anonymous.catering.response.ResponseCreatePemesanan;
import com.example.anonymous.catering.response.ResponseGambar;
import com.example.anonymous.catering.response.ResponseGuru;
import com.example.anonymous.catering.response.ResponseLogin;
import com.example.anonymous.catering.response.ResponseLoginMember;
import com.example.anonymous.catering.response.ResponseMatpel;
import com.example.anonymous.catering.response.ResponseCreateMurid;
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

public interface ApiInterface {
    @GET("matpel/find-all")
    Call<ResponseMatpel> matpelFindAll();

    @GET("guru/find-all")
    Call<ResponseGuru> guruFindAll();

    @GET("guru/find-by-id")
    Call<ResponGuruDetail> guruFindById(@Query("id") int id);

    @GET("paket/find-all")
    Call<ResponsePaket> paketFindAll();


    @GET("paket/find-by-id")
    Call<ResponsePaketById> paketFindById(@Query("nama_paket") String nama_paket);

    @FormUrlEncoded
    @POST("pemesan/create")
    Call<ResponseCreatePemesanan> simpanPemesanan(@Field("member_id") int member_id,
                                                  @Field("nama_paket") String nama_paket,
                                                  @Field("jumlah_pesanan") int jumlah_pesanan,
                                                  @Field("total") int total,
                                                  @Field("alamat_lengkap") String alamat_lengkap,
                                                  @Field("latitude") Double latitude,
                                                  @Field("longitude") Double longitude,
                                                  @Field("pesan_tambahan") String pesan_tambahan);

//    @FormUrlEncoded
//    @POST("pemesan/create")
//    Call<ResponseCreateMember> simpanMember(@Field("member_id") int member_id,
//                                                  @Field("nama_paket") String nama_paket,
//                                                  @Field("jumlah_pesanan") int jumlah_pesanan,
//                                                  @Field("total") int total,
//                                                  @Field("alamat_lengkap") String alamat_lengkap,
//                                                  @Field("latitude") Double latitude,
//                                                  @Field("longitude") Double longitude,
//                                                  @Field("pesan_tambahan") String pesan_tambahan);

    @FormUrlEncoded
    @POST("murid/update")
    Call<ResponseCreateMurid> updateMurid(@Field("id") Integer id,
                                          @Field("nama") String nama,
                                          @Field("no_hp") String no_hp,
                                          @Field("email") String email,
                                          @Field("alamat") String alamat,
                                          @Field("jk") String jk,
                                          @Field("nisn") String nisn,
                                          @Field("kelas") String kelas,
                                          @Field("nama_sekolah") String nama_sekolah,
                                          @Field("photo") String photo);

    //login member
    @FormUrlEncoded
    @POST("login/member")
    Call<ResponseLoginMember> memberLogin(
            @Field("username") String username,
            @Field("password") String password);

    @GET("pemesan/find-by-member")
    Call<ResponsePemesanan> pemesananFindByMember(@Query("id") String id);

    @GET("pembayaran/find-by-pemesanan")
    Call<ResponsePembayaran> pembayaranFindByPemesanan(@Query("id") String id);

    @GET("v1/pemesan/find")
    Call<ResponsePemesananJoin> pemesananJoin(@Query("id") String id);

    @Multipart
    @POST("upload/uploadimage.php")
    Call<ResponseGambar> uploadImage(@Part MultipartBody.Part image,
                                     @Part("pemesanan_id") RequestBody pemesanan,
                                     @Part("jumlah_transfer") RequestBody jumlah_transfer,
                                     @Part("total") RequestBody total);
}
