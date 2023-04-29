package com.example.yemeksiparisapplication.userİnterface.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapplication.R
import com.example.yemeksiparisapplication.data.entity.Sepet
import com.example.yemeksiparisapplication.data.entity.Yemekler
import com.example.yemeksiparisapplication.databinding.CardBinding
import com.example.yemeksiparisapplication.userİnterface.fragment.AnasayfaFragmentDirections

class YemeklerAdapter(var mContext: Context, var yemeklerListesi:List<Yemekler>)
    : RecyclerView.Adapter<YemeklerAdapter.SatirTasarimTutucu>() {

    inner class SatirTasarimTutucu(var binding:CardBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatirTasarimTutucu {
        val binding:CardBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.card,parent,false)
        return SatirTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    override fun onBindViewHolder(holder: SatirTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.binding

        t.dataYemek = yemek
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        loadImage(imageUrl = url,holder.binding.imageFood)

        t.imageCart.setOnClickListener {
            val sepet = Sepet(
                0,
                yemek.yemek_adi,
                yemek.yemek_resim_adi,
                yemek.yemek_fiyat,
                1,
                "Onurcan"
            )


            val directions = AnasayfaFragmentDirections.anasayfaToDetay(sepet)
            Navigation.findNavController(it).navigate(directions)
        }

    }

    fun loadImage(imageUrl:String,imageView: ImageView){
        Glide.with(mContext)
            .load(imageUrl)
            .into(imageView)
    }

}