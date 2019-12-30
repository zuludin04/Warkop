package com.app.zuludin.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.common.base.BaseFragment
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.detail.adapter.CafeSlideAdapter
import com.app.zuludin.detail.adapter.FacilityAdapter
import com.app.zuludin.detail.adapter.ReviewAdapter
import com.app.zuludin.detail.databinding.FragmentCafeDetailBinding
import kotlinx.android.synthetic.main.fragment_cafe_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class CafeDetailFragment : BaseFragment() {

    private val args: CafeDetailFragmentArgs by navArgs()
    private val viewModel: CafeDetailViewModel by viewModel()
    private lateinit var dataBinding: FragmentCafeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cafe_detail, container, false)

        dataBinding.viewModel = viewModel
        dataBinding.timeoutLayout.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cafe = args.detailCafe
        val facilityAdapter = FacilityAdapter(ArrayList())
        val reviewAdapter = ReviewAdapter(ArrayList())

        dataBinding.recyclerMore.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = facilityAdapter
        }

        dataBinding.recyclerReview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reviewAdapter
        }

        viewModel.cafe.observe(this, Observer {
            it?.let { cafe ->
                detail_cafe_slide.adapter = cafe.photos?.let { photo -> CafeSlideAdapter(photo) }
                detail_cafe_map.setOnClickListener {
                    openMap(cafe.location?.latitude, cafe.location?.longitude)
                }
                detail_cafe_phone.setOnClickListener { callCafe(cafe.phoneNumbers) }
                facilityAdapter.addFacilities(cafe.highlights)
            }
        })

        viewModel.loadCafeDetail(cafe)
    }

    override fun getViewModel(): BaseViewModel = viewModel

    private fun openMap(latitude: String?, longitude: String?) {
        val uri = String.format(
            Locale.ENGLISH,
            "http://maps.google.com/maps?q=loc:%s,%s",
            latitude,
            longitude
        )
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    private fun callCafe(phone: String?) {
        val uri = "tel:$phone"
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
        startActivity(intent)
    }
}
