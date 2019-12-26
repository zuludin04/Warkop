package com.app.zuludin.home

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.common.base.BaseFragment
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.common.EqualSpacingItemDecoration
import com.app.zuludin.home.adapter.HomeLayoutAdapter
import com.app.zuludin.home.databinding.FragmentHomeBinding
import com.app.zuludin.home.navigation.HomeNavigation
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search_menu) {
            findNavController().navigate(R.id.actionToSearchPage)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewModel(): BaseViewModel = viewModel

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

