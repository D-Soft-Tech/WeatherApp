package com.example.ey.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ey.R
import com.example.ey.databinding.ListingScreenBinding
import com.example.ey.model.FinalCityWeather
import com.example.ey.ui.MainViewModel
import com.example.ey.ui.recyclerView.RecyclerViewAdapter
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
                            recyclerViewAdapter.setCitiesWeathers(
                                tempList
                            )
                        }
                    )
                }
            }
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

                override fun onQueryTextChange(newText: String?): Boolean {
                    tempList.clear()
                    val searchText = newText?.toLowerCase(Locale.getDefault())?.trim()
                    searchText?.let { schTxt ->
                        permanentList.forEach { weather ->
                            if (weather.dataFromMapperClass.cityName!!.toLowerCase(Locale.getDefault())
                                .contains(schTxt)
                            ) {
                                tempList.add(weather)
                            }
                        }
                        recyclerViewAdapter.setCitiesWeathers(tempList)
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
//        recyclerViewAdapter.setCitiesWeathers(permanentList)
//        viewModel.getWeather().observe(
//            viewLifecycleOwner,
//            { cityWeathers ->
//                recyclerViewAdapter.setCitiesWeathers(
//                    cityWeathers
//                )
//            }
//        )
        recyclerViewAdapter.setCitiesWeathers(permanentList)
    }
}
