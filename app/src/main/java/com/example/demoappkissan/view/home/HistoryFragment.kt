package com.example.demoappkissan.view.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.demoappkissan.R
import com.example.demoappkissan.adapter.HistoryListAdapter
import com.example.demoappkissan.extensions.showMessage
import com.example.demoappkissan.retrofit.RetrofitFactory
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class HistoryFragment : Fragment() {

    val adapter by lazy { HistoryListAdapter() }
    var viewModel: HistoryViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setAdapter()
        addObserver()
    }

    private fun setAdapter() {
        rvHistoryDetails.layoutManager =
            LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
        rvHistoryDetails.adapter = adapter
    }

    private fun addObserver() {
        if (viewModel != null) return

        viewModel = ViewModelProvider(this@HistoryFragment).get(HistoryViewModel::class.java)

        viewModel?.getAllHistoryData()?.observe(viewLifecycleOwner, Observer {
            it?.apply {
                adapter.addData(this)
            }
        })
    }

}
