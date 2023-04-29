package com.example.yemeksiparisapplication.userİnterface.adapter

import android.annotation.SuppressLint
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
import com.example.yemeksiparisapplication.databinding.CardCartBinding
import com.example.yemeksiparisapplication.userİnterface.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparisapplication.userİnterface.viewmodel.SepetViewModel

class SepetAdapter(var mContext: Context, var yemeklerListesi:List<Sepet>,var viewModel:SepetViewModel)
    : RecyclerView.Adapter<SepetAdapter.SatirTasarimTutucu>() {

    var onItemClickListener: ((ImageView, Int) -> Unit)? = null
    inner class SatirTasarimTutucu(var binding: CardCartBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatirTasarimTutucu {
        val binding: CardCartBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.card_cart,parent,false)
        return SatirTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: SatirTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi[position]
        val t = holder.binding

        t.dataYemek = yemek
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        loadImage(imageUrl = url,holder.binding.imageFood)

        t.imageCart.setOnClickListener {
            onItemClickListener?.invoke(holder.binding.imageCart,position)
        }

    }

    fun loadImage(imageUrl:String,imageView: ImageView){
        Glide.with(mContext)
            .load(imageUrl)
            .into(imageView)
    }

}