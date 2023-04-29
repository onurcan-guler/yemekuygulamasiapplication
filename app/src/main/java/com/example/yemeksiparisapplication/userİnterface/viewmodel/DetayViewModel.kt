package com.example.yemeksiparisapplication.userİnterface.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapplication.data.entity.Sepet
import com.example.yemeksiparisapplication.data.entity.Yemekler
import com.example.yemeksiparisapplication.data.repo.YemeklerDaoRepos

class DetayViewModel : ViewModel() {
    val yRepo = YemeklerDaoRepos()

    var yemekSepetList: MutableLiveData<List<Sepet>>

    init {
        yemekSepetList = MutableLiveData()
    }


    fun sepetYemeklerGetir(username: String) {
        yRepo.sepetYemekYukle(username)
        yemekSepetList = yRepo.sepetYemekGetir()

    }

    fun sepetYemekYukle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        yRepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    fun sepetSil(sepet_yemek_id: Int, kullanici_adi: String){
        yRepo.sil(sepet_yemek_id,kullanici_adi)
        sepetYemeklerGetir(kullanici_adi)
    }

}