package com.app.zuludin.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.common.EqualSpacingItemDecoration
import com.app.zuludin.common.base.BaseFragment
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.search.databinding.FragmentSearchBinding
import com.app.zuludin.search.navigation.SearchRestaurantNavigation
import com.app.zuludin.search.view.SearchRestaurantAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRestaurantFragment : BaseFragment() {

    private lateinit var dataBinding: FragmentSearchBinding
    private val viewModel: SearchRestaurantViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.toolbar.setNavigationOnClickListener { it.findNavController().popBackStack() }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.search.setIconifiedByDefault(false)
        dataBinding.search.queryHint = "Search"

        setupRecycler()
        searchRestaurant()
    }

    override fun getViewModel(): BaseViewModel = viewModel

    private fun setupRecycler() {
        val searchAdapter = SearchRestaurantAdapter(ArrayList()) {
            it.restaurant?.let { restaurant ->
                findNavController().navigate(
                    SearchRestaurantNavigation.detailLayout(
                        restaurant.id,
                        restaurant.name
                    )
                )
            }
        }

        dataBinding.recyclerResult.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.VERTICAL))
            adapter = searchAdapter
        }
    }

    private fun searchRestaurant() {
        dataBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchRestaurant(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }
}