package com.example.akharinkhabar.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import com.example.akharinkhabar.R
import com.example.akharinkhabar.databinding.FragmentMainBinding
import com.example.akharinkhabar.other.Event
import com.example.akharinkhabar.other.Status
import com.example.akharinkhabar.ui.adapters.AdapterMain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.E

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    private val viewModel: ViewModelMain by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.value.observe(viewLifecycleOwner) { list ->
                binding?.mainList?.adapter = AdapterMain().also {
                    Log.i("TAG", "onViewCreated: $list ")
                    it.setList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data?.collectLatest { Event ->
                when (Event.getContentIfNotHandled()?.statusResource) {
                    Status.SUCCESS -> {
                        binding?.titleLoading?.isVisible = false
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            Event.getContentIfNotHandled()?.errorModel?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Status.LOADING -> {
                        binding?.titleLoading?.isVisible = true
                    }
                    Status.OFFLINE -> {
                        binding?.titleLoading?.isVisible = false
                        Toast.makeText(requireContext(), "Check your net", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
        }


    }
}