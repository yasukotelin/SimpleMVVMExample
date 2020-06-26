package com.github.yasukotelin.simplemvvmexample

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NextPageFragment : Fragment() {

    companion object {
        fun newInstance() = NextPageFragment()
    }

    private lateinit var viewModel: NextPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NextPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}