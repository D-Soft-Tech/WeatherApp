package com.example.ey.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ey.R
import com.example.ey.databinding.ListingScreenBinding
import com.example.ey.model.FinalCityWeather
import com.example.ey.ui.MainViewModel
import com.example.ey.ui.recyclerView.RecyclerViewAdapter
import com.example.ey.ui.recyclerView.hide
import com.example.ey.ui.recyclerView.show
import dagger.hilt.android.AndroidEntryPoint
import java.util.* // ktlint-disable no-wildcard-imports
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ListingsScreen : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var permanentList: List<FinalCityWeather> // Keeps track of the original list
    private lateinit var tempList: ArrayList<FinalCityWeather> // Used to perform filter operations

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var binding: ListingScreenBinding
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.listing_screen, container, false)
        recyclerViewAdapter = RecyclerViewAdapter()

        // Variable initialization
        permanentList = arrayListOf()
        tempList = arrayListOf()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = binding.progressBar // Initialize the progress bar
        progressBar.show() // Show the progress bar

        viewModel.hasAllCitiesBeenLoaded.observe(
            viewLifecycleOwner,
            { check ->
                if (check) {
                    viewModel.getWeather().observe(
                        viewLifecycleOwner,
                        { cityWeathers ->
                            tempList.clear()
                            tempList.addAll(cityWeathers)
                            permanentList = cityWeathers

                            progressBar.hide() // Hide the progress bar

                            recyclerViewAdapter.setCitiesWeathers(
                                tempList
                            )
                        }
                    )
                }
            }
        )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.menu_item, menu)
        (menu.findItem(R.id.search_list).actionView as SearchView).setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {
                    tempList.clear()
                    val searchText = newText?.lowercase()?.trim()
                    searchText?.let { schTxt ->
                        tempList.clear()
                        permanentList.forEach { weather ->
                            if (weather.dataFromMapperClass.cityName!!.lowercase()
                                .contains(schTxt)
                            ) {
                                tempList.add(weather)
                            }
                        }
                        recyclerViewAdapter.setCitiesWeathers(tempList)
                        recyclerViewAdapter.notifyDataSetChanged()
                    } ?: run {
                        tempList.clear()
                        tempList.addAll(permanentList)
                        recyclerViewAdapter.setCitiesWeathers(tempList)
                    }
                    return false
                }
            })
    }

    override fun onResume() {
        super.onResume()
        recyclerViewAdapter.setCitiesWeathers(permanentList)
    }
}
