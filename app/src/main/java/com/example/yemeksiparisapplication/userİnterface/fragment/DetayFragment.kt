package com.example.yemeksiparisapplication.userİnterface.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yemeksiparisapplication.R
import com.example.yemeksiparisapplication.databinding.FragmentDetayBinding
import com.example.yemeksiparisapplication.userİnterface.viewmodel.DetayViewModel
import com.google.android.material.snackbar.Snackbar


class DetayFragment : Fragment() {

    private lateinit var binding:FragmentDetayBinding
    private lateinit var viewModel:DetayViewModel

    private var count:Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  tempViewModel: DetayViewModel by viewModels()
        viewModel =  tempViewModel
        viewModel.sepetYemeklerGetir("Onurcan")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarCart.setNavigationOnClickListener {
            Navigation.findNavController(it).navigate(R.id.anasayfaFragment)
        }

        val bundle: DetayFragmentArgs by navArgs()
        val data = bundle.sepet

        binding.dataSepet = data

        binding.editCount.setText(data.yemek_siparis_adet.toString())

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${data.yemek_resim_adi}"
        loadImage(imageUrl = url, binding.imageViewCart)

        binding.editCount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val inputValue = p0.toString()
                if (inputValue.isNotEmpty()) {
                    val intValue = inputValue.toInt()
                    count = intValue
                    println(count)
                    updateCountAndButtonStatus(intValue,data.yemek_fiyat)
                }

            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.buttonAdd.setOnClickListener {
            viewModel.sepetYemeklerGetir("Onurcan")
            val gelensayi = viewModel.yemekSepetList.value?.size ?: 0
            var geciciAdet = 0
            var sayac = 0

            for (x in 0 until gelensayi) {
                if (data.yemek_adi == viewModel.yemekSepetList.value?.get(x)?.yemek_adi){
                    geciciAdet = viewModel.yemekSepetList.value?.get(x)?.yemek_siparis_adet!!
                    viewModel.sepetSil(viewModel.yemekSepetList.value?.get(x)?.sepet_yemek_id!!,"Onurcan")
                    sayac++
                }
            }

            viewModel.sepetYemekYukle(data.yemek_adi, data.yemek_resim_adi, data.yemek_fiyat, (count + geciciAdet), "Onurcan")
            println(gelensayi)
            println(geciciAdet)

            if (sayac == 0) {
                Snackbar.make(it, "Sepete ${data.yemek_adi} Eklendi", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(it, "Sepetteki ${data.yemek_adi} Güncellendi.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCountAndButtonStatus(count: Int, price: Int) {
        val totalPrice = count * price
        binding.textViewPrice.text = getString(R.string.price, totalPrice)
        binding.buttonAdd.isEnabled = count > 1
    }

    private fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(requireContext())
            .load(imageUrl)
            .into(imageView)
    }


}
