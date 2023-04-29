package com.example.yemeksiparisapplication.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yemeksiparisapplication.data.entity.*
import com.example.yemeksiparisapplication.retrofit.ApiUtils
import com.example.yemeksiparisapplication.retrofit.SerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepos() {

    var yemekDao: SerDao

    var yemekListesi: MutableLiveData<List<Yemekler>>

    var yemekSepetListesi: MutableLiveData<List<Sepet>>

    init {
        yemekDao = ApiUtils.getServiceDao()
        yemekListesi = MutableLiveData()
        yemekSepetListesi = MutableLiveData()
    }

    fun yemekGetir(): MutableLiveData<List<Yemekler>> {
        return yemekListesi
    }

    fun sepetYemekGetir(): MutableLiveData<List<Sepet>> {
        return yemekSepetListesi
    }


    fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        yemekDao.sepetEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        ).enqueue(object : Callback<CrudCevap> {
            override fun onResponse(call: Call<CrudCevap>, response: Response<CrudCevap>) {
            }

            override fun onFailure(call: Call<CrudCevap>, t: Throwable) {
            }
        })
    }

    fun sil(sepet_yemek_id: Int, kullanici_adi: String) {
        yemekDao.sepetYemekSil(sepet_yemek_id, kullanici_adi).enqueue(object :
            Callback<CrudCevap> {

            override fun onResponse(call: Call<CrudCevap>, response: Response<CrudCevap>) {
            }

            override fun onFailure(call: Call<CrudCevap>, t: Throwable) {

            }
        })
    }

    fun yemekYukle() {
        yemekDao.tumYemekleriGetir().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>, response: Response<YemeklerCevap>) {
                println("yemekYukle")
                val liste = response.body()!!.yemekler
                yemekListesi.value = liste

            }

            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {
                println(t.message)
            }
        })
    }


    fun sepetYemekYukle(kullanici_adi: String) {

        yemekDao.sepetGetir(kullanici_adi).enqueue(object : Callback<SepetCevap> {

            override fun onResponse(call: Call<SepetCevap>, response: Response<SepetCevap>) {
                println("sepetYemekYukle")

                val liste = response.body()!!.sepet_yemekler
                yemekSepetListesi.value = liste

            }

            override fun onFailure(call: Call<SepetCevap>, t: Throwable) {
                println(t.message)
            }
        })
    }


}