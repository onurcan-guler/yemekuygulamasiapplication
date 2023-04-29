package com.example.yemeksiparisapplication.userİnterface.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemeksiparisapplication.R
import com.example.yemeksiparisapplication.databinding.FragmentAnasayfaBinding
import com.example.yemeksiparisapplication.userİnterface.adapter.YemeklerAdapter
import com.example.yemeksiparisapplication.userİnterface.viewmodel.AnasayfaViewModel


class AnasayfaFragment : Fragment() {

    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var viewModel:AnasayfaViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarMain.setNavigationOnClickListener {
            Navigation.findNavController(view).navigate(R.id.sepetFragment)
        }


        viewModel.yemekList.observe(viewLifecycleOwner) { meals ->
            val adapter = YemeklerAdapter(requireContext(),meals)
            binding.adapterAna = adapter
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

}
