package com.example.yemeksiparisapplication.userİnterface.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapplication.data.entity.Yemekler
import com.example.yemeksiparisapplication.data.repo.YemeklerDaoRepos

class AnasayfaViewModel:ViewModel() {
    val yRepo = YemeklerDaoRepos()
    var yemekList: MutableLiveData<List<Yemekler>>

    init {
        yemeklerGetir()
        yemekList = yRepo.yemekGetir()
    }

    private fun yemeklerGetir(){
        yRepo.yemekYukle()
    }
}