package com.github.yasukotelin.simplemvvmexample

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.yasukotelin.simplemvvmexample.databinding.FragmentHelloBinding

class HelloFragment : Fragment() {

    companion object {
        fun newInstance() = HelloFragment()
    }

    private lateinit var helloViewModel: HelloViewModel
    private lateinit var dataBinding: FragmentHelloBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        helloViewModel = ViewModelProviders.of(this).get(HelloViewModel::class.java)
        dataBinding = FragmentHelloBinding.inflate(inflater, container, false).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = helloViewModel
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        helloViewModel.showTokenAction.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it,  Toast.LENGTH_SHORT).show()
        })
        helloViewModel.navigateNextAction.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_helloFragment_to_nextPageFragment)
        })
    }
}