package com.example.yemeksiparisapplication.userİnterface.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapplication.data.entity.Sepet
import com.example.yemeksiparisapplication.data.repo.YemeklerDaoRepos

class SepetViewModel:ViewModel() {
    val yRepo = YemeklerDaoRepos()

    var yemekSepetList: MutableLiveData<List<Sepet>>

    init {
        yemekSepetList = MutableLiveData()
    }


    fun sepetYemeklerGetir(username: String) {
        yRepo.sepetYemekYukle(username)
        yemekSepetList = yRepo.sepetYemekGetir()
    }


    fun sepetSil(sepet_yemek_id: Int, kullanici_adi: String){
        yRepo.sil(sepet_yemek_id,kullanici_adi)

        sepetYemeklerGetir(username = kullanici_adi)
    }
}