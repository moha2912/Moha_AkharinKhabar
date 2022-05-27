package com.example.akharinkhabar.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.akharinkhabar.R
import com.example.akharinkhabar.databinding.FragmentMainBinding
import com.example.akharinkhabar.other.Status
import com.example.akharinkhabar.ui.adapters.AdapterMain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ViewModelMain by viewModels()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()
        recyclerView = binding.mainList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = AdapterMain()
        recyclerView.adapter = adapter

        lifecycle.coroutineScope.launch {
            viewModel.list.collect {
                adapter.submitList(it)

//                binding.mainList.isVisible = true
                binding.loading.isVisible = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data?.collectLatest { Event ->
                when (Event.getContentIfNotHandled()?.statusResource) {
                    Status.SUCCESS -> {
                        binding.titleLoading.isVisible = false
                        binding.mainList.isVisible = true
                        binding.loading.isVisible = false
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            Event.getContentIfNotHandled()?.errorModel?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Status.LOADING -> {
                        binding.titleLoading.isVisible = true
//                        binding.mainList.isVisible = false
                        binding.loading.isVisible = true
                    }
                    Status.OFFLINE -> {
                        binding.titleLoading.isVisible = false
//                        binding.mainList.isVisible = true
                        binding.loading.isVisible = false
                        Toast.makeText(requireContext(), "Check your net", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
        }


    }
}