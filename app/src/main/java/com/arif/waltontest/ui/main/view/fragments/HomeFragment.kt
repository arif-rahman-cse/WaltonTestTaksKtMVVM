package com.arif.waltontest.ui.main.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arif.waltontest.R
import com.arif.waltontest.data.api.ApiHelper
import com.arif.waltontest.data.api.RetrofitBuilder
import com.arif.waltontest.data.model.RpProduct
import com.arif.waltontest.databinding.FragmentHomeBinding
import com.arif.waltontest.ui.base.ViewModelFactory
import com.arif.waltontest.ui.main.adapter.MainAdapter
import com.arif.waltontest.ui.main.viewmodel.MainViewModel
import com.arif.waltontest.utils.Status
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

private const val TAG = "HomeFragment"


class HomeFragment() : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productTbl.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabId = tab.position
                if (tabId == 0) {
                    Log.d(TAG, "onTabSelected: Computer: "+tabId)



                } else {
                    Log.d(TAG, "onTabSelected: Mobile:"+tabId)
                    setupObservers()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        setupViewModel()
        setupUI()
        setupObservers()
    }



    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {


        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getProduct().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d(TAG, "setupObservers: Success"+Status.SUCCESS)
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { products -> retrieveList(products) }
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "setupObservers: Error "+Status.ERROR)
                        Log.d(TAG, "setupObservers: Error: "+ resource.message.toString())
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.d(TAG, "setupObservers: Loading")
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(product: List<RpProduct>) {
        val tempProduct = mutableListOf<RpProduct>()
        //val tempProduct: List<RpProduct> = product


        adapter.apply {
            for (item in product) {
                //println(name.productType)

                if (item.productType.equals("Computer")){
                    Log.d(TAG, "retrieveList: "+item.productType)
                    tempProduct.add(item)
                }else{
                }
            }
            addProduct(tempProduct)
            notifyDataSetChanged()
        }
    }
}