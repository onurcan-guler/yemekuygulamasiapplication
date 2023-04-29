package com.example.yemeksiparisapplication.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"
        fun getServiceDao(): SerDao {
            return RetrofitClient.getClient(BASE_URL).create(SerDao::class.java)
        }
    }
}