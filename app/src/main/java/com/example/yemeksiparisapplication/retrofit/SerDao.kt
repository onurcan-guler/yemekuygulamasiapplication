package com.example.yemeksiparisapplication.retrofit

import com.example.yemeksiparisapplication.data.entity.CrudCevap
import com.example.yemeksiparisapplication.data.entity.SepetCevap
import com.example.yemeksiparisapplication.data.entity.YemeklerCevap
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface SerDao {
    @GET("tumYemekleriGetir.php")
    fun tumYemekleriGetir(): Call<YemeklerCevap>

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepetGetir(@Field("kullanici_adi") kullanici_adi: String): Call<SepetCevap>


    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepetEkle(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): Call<CrudCevap>

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepetYemekSil(
        @Field("sepet_yemek_id") sepet_yemek_id: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): Call<CrudCevap>
}