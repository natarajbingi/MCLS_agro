package com.slcm.slcmagroapp.views.pdf

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.FragmentFabBinding
import com.slcm.slcmagroapp.views.fab.FabMenuAdapter
import com.slcm.slcmagroapp.views.fab.FabViewModel
import com.slcm.slcmagroapp.views.fab.ItemsModel
import com.slcm.slcmagroapp.views.fab.OnFabItemClickListener

// Certification & Reports
@RequiresApi(Build.VERSION_CODES.M)
class PdfFragment : Fragment(), OnFabItemClickListener, OnPageChangeListener,
    OnLoadCompleteListener,
    OnPageErrorListener {

    companion object {
        fun newInstance() = PdfFragment()
        val TAG: String = PdfFragment::class.java.simpleName
    }

    private lateinit var viewModel: FabViewModel
    private lateinit var binding: FragmentFabBinding
    private val adapter = FabMenuAdapter(this)

    //    FAB Menu ArrayList SLCM, Feature. Advantage. Benefits
    private var a2500 = mutableListOf<ItemsModel>()
    private var a2800 = mutableListOf<ItemsModel>()
    private var a4000 = mutableListOf<ItemsModel>()
    private var a4300 = mutableListOf<ItemsModel>()
    private var a4500 = mutableListOf<ItemsModel>()
    private var a4800 = mutableListOf<ItemsModel>()

    init {
        a2500.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "COMPLIANCE UNDER IS-4925",
                listOf(R.string.a2500_compliIance_under_is_4925)
            )
        )
        a2500.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "COMPRESSIVE STRENGTH TEST REPORT",
                listOf(R.string.a2500_compressive_strength_test_report)
            )
        )
        a2500.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "HOMOGENITY STRENGTH TEST REPORT",
                listOf(R.string.a2500_homogenity_test_report)
            )
        )

        a2800.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "COMPLIANCE UNDER IS-4925",
                listOf(R.string.a2800_compliIance_under_is_4925)
            )
        )

        a4000.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "COMPLIANCE UNDER IS-4925",
                listOf(R.string.a4000_compliIance_under_is_4925)
            )
        )
        a4000.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "COMPRESSIVE STRENGTH TEST REPORT",
                listOf(R.string.a4000_compressive_strength_test_report)
            )
        )
        a4000.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "HOMOGENITY STRENGTH TEST REPORT",
                listOf(R.string.a4000_homogenity_test_report)
            )
        )

        a4300.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "COMPLIANCE UNDER IS-4925",
                listOf(R.string.a4300_compliIance_under_is_4925)
            )
        )

        a4500.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "COMPLIANCE UNDER IS-4925",
                listOf(R.string.a4500_compliIance_under_is_4925)
            )
        )

        a4800.add(
            ItemsModel(
                R.drawable.wheel_steering,
                "COMPLIANCE UNDER IS-4925",
                listOf(R.string.a4800_compliIance_under_is_4925)
            )
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fab, container, false)
        viewModel = ViewModelProvider(this)[FabViewModel::class.java]
        binding.fabVModel = viewModel
        binding.recyclerView.adapter = adapter

        binding.statsGroupCerti.visibility = View.VISIBLE
        binding.pdfv.visibility = View.VISIBLE
        binding.statsGroupCS.visibility = View.GONE
        binding.statsGroupFab.visibility = View.GONE
        binding.middleList.visibility = View.GONE
        binding.statsGroupCompeteStudy.visibility = View.GONE


        adapter.submitList(a2500)
        setPdfToView(getString(a2500[0].list[0]))

        binding.statsGroupCerti.setOnCheckedChangeListener(object :
            RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.argo_2500_menu -> {
                        adapter.submitList(a2500)
                        setPdfToView(getString(a2500[0].list[0]))
                    }
                    R.id.argo_2800_menu -> {
                        adapter.submitList(a2800)
                        setPdfToView(getString(a2800[0].list[0]))
                    }
                    R.id.argo_4000_menu -> {
                        adapter.submitList(a4000)
                        setPdfToView(getString(a4000[0].list[0]))
                    }
                    R.id.argo_4300_menu -> {
                        adapter.submitList(a4300)
                        setPdfToView(getString(a4300[0].list[0]))
                    }
                    R.id.argo_4500_menu -> {
                        adapter.submitList(a4500)
                        setPdfToView(getString(a4500[0].list[0]))
                    }
                    R.id.argo_4800_menu -> {
                        adapter.submitList(a4800)
                        setPdfToView(getString(a4800[0].list[0]))
                    }
                }
            }

        })
        return binding.root
    }

    private fun setPdfToView(pdfFile: String) {
        Log.e(TAG, "setPdfToView: $pdfFile")
        binding.pdfv.fromAsset(pdfFile)
            // .defaultPage(pageNumber)
            .onPageChange(this)
            .enableAnnotationRendering(true)
            .onLoad(this)
            .scrollHandle(DefaultScrollHandle(requireContext()))
            .spacing(10) // in dp
            .onPageError(this)
            .load()
    }

    override fun onItemClick(item: ItemsModel, position: Int) {
        setPdfToView(getString(item.list[0]))
        adapter.notifyDataSetChanged()
    }

    override fun onItemLongClick(item: ItemsModel, position: Int) {

    }

    override fun onPageChanged(page: Int, pageCount: Int) {

    }

    override fun loadComplete(nbPages: Int) {
    }

    override fun onPageError(page: Int, t: Throwable?) {
        Log.e(TAG, "Error : ${t?.stackTrace}")
    }


}