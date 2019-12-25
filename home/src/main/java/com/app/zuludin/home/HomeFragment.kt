package com.app.zuludin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.common.EqualSpacingItemDecoration
import com.app.zuludin.home.adapter.HomeLayoutAdapter
import com.app.zuludin.home.databinding.FragmentHomeBinding
import com.app.zuludin.home.navigation.HomeNavigation
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        dataBinding.viewModel = viewModel
        dataBinding.errorLayout.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val homeAdapter = HomeLayoutAdapter(ArrayList()) { city, category, restaurant ->
            if (city != null) {
                findNavController().navigate(
                    HomeNavigation.listLayout(city.id, city.name, "city")
                )
            }

            if (category != null) {
                findNavController().navigate(
                    HomeNavigation.listLayout(category.id, category.name, "category")
                )
            }

            if (restaurant != null) {
                val args = Bundle()
                args.putString("detailCafe", restaurant.restaurant?.id)
                args.putString("detailTitle", restaurant.restaurant?.name)
                findNavController().navigate(R.id.actionStraightToDetail, args)
            }
        }

        home_recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(EqualSpacingItemDecoration(8, EqualSpacingItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }
}

