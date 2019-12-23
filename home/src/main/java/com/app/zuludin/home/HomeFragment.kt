package com.app.zuludin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.common.EqualSpacingItemDecoration
import com.app.zuludin.home.adapter.HomeLayoutAdapter
import com.app.zuludin.home.navigation.HomeNavigation
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        viewModel.restaurant.observe(this, Observer {
            it?.let { items ->
                homeAdapter.addItemList(items.data)
            }
        })
    }
}

/*view.view_pager.adapter = PagerAdapter(cities) {
    findNavController().navigate(
        HomeNavigation.listLayout(
            Gson().toJson(cities[it]),
            cities[it].name
        )
    )
}
view.view_pager.setPageTransformer(ZoomOutTransformation())

TabLayoutMediator(
view.tabs,
view.view_pager,
TabLayoutMediator.TabConfigurationStrategy { tab, position ->
    val customTab = LayoutInflater.from(requireActivity().applicationContext)
        .inflate(R.layout.custom_tab, null)
    customTab.tabTextView.text = cities[position].name
    tab.customView = customTab
}).attach()*/
