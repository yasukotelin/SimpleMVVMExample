package com.github.yasukotelin.simplemvvmexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.yasukotelin.simplemvvmexample.databinding.FragmentHelloBinding

class HelloFragment : Fragment() {

    companion object {
        fun newInstance() = HelloFragment()
    }

    private val helloViewModel: HelloViewModel by viewModels()
    private lateinit var dataBinding: FragmentHelloBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentHelloBinding.inflate(inflater, container, false).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = helloViewModel
        }

        observeHelloViewModel()

        return dataBinding.root
    }

    private fun observeHelloViewModel() {
        helloViewModel.navigateNextAction.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_helloFragment_to_nextPageFragment)
        })
        helloViewModel.showToastAction.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}