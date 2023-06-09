package com.example.yemeksiparisapplication.userİnterface.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemeksiparisapplication.R
import com.example.yemeksiparisapplication.databinding.FragmentSepetBinding
import com.example.yemeksiparisapplication.userİnterface.adapter.SepetAdapter
import com.example.yemeksiparisapplication.userİnterface.viewmodel.SepetViewModel


class SepetFragment : Fragment() {

    private lateinit var binding:FragmentSepetBinding
    private lateinit var viewModel: SepetViewModel
    private lateinit var adapter: SepetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:SepetViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.sepetYemeklerGetir("Onurcan")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SepetAdapter(requireContext(), emptyList(), viewModel)

        binding.adapter = adapter

        binding.toolbarCart.setNavigationOnClickListener {
            Navigation.findNavController(it).navigate(R.id.anasayfaFragment)
        }

        viewModel.yemekSepetList.observe(viewLifecycleOwner){sepet->
            adapter = SepetAdapter(requireContext(),sepet,viewModel)
            binding.adapter = adapter

            adapter.onItemClickListener = {v,p->
                if(p !=0){

                    viewModel.sepetSil(sepet[p].sepet_yemek_id,sepet[p].kullanici_adi)
                }else{
                    binding.adapter = null
                }
            }
        }


    }


}
