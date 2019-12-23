package com.app.zuludin.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.common.EqualSpacingItemDecoration
import com.app.zuludin.common.EqualSpacingItemDecoration.Companion.VERTICAL
import com.app.zuludin.list.adapter.CafeListAdapter
import com.app.zuludin.list.databinding.FragmentCafeListBinding
import com.app.zuludin.list.navigation.CafeListDirection
import kotlinx.android.synthetic.main.fragment_cafe_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class CafeListFragment : Fragment() {

    private val args: CafeListFragmentArgs by navArgs()
    private val viewModel: CafeListViewModel by viewModel()
    private lateinit var dataBinding: FragmentCafeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cafe_list, container, false)

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = args.city
        val type = args.type

        val cafeAdapter =
            CafeListAdapter(ArrayList()) { id, title ->
                findNavController().navigate(
                    CafeListDirection.detailLayout(id, title)
                )
            }

        recycler_cafe.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(EqualSpacingItemDecoration(32, VERTICAL))
            setHasFixedSize(true)
            adapter = cafeAdapter
        }

        viewModel.cafes.observe(this, Observer {
            it?.let { cafes ->
                cafeAdapter.addItems(cafes.data)
            }
        })

        when (type) {
            "city" -> viewModel.loadCafes(itemId, "city", null)
            "category" -> viewModel.loadCafes(null, null, itemId)
        }
    }
}
