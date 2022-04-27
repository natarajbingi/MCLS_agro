package com.slcm.slcmagroapp.views.competestudy

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.FragmentFabBinding
import com.slcm.slcmagroapp.views.cs.MidImgAdapter
import com.slcm.slcmagroapp.views.fab.FabMenuAdapter
import com.slcm.slcmagroapp.views.fab.FabViewModel
import com.slcm.slcmagroapp.views.fab.ItemsModel
import com.slcm.slcmagroapp.views.fab.OnFabItemClickListener

/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
@RequiresApi(Build.VERSION_CODES.M)
class CompetetionStudyFragment : Fragment(), OnFabItemClickListener {

    private lateinit var binding: FragmentFabBinding
    private lateinit var viewModel: FabViewModel
    private lateinit var ctx: Context
    val adapter = FabMenuAdapter(this)
    private val adapterMid = MidImgAdapter(this)

    //    FAB Menu ArrayList SLCM, Feature. Advantage. Benefits
    private var itemsAjaxVsXcmg = mutableListOf<ItemsModel>()

    init {
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Company Information", listOf(R.drawable.competes1)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Product technical specification", listOf(R.drawable.competes2)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Misleading practice by XCMG", listOf(R.drawable.competes3)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "AJAX ARGO 4300 Vs XCMG - Productivity", listOf(R.drawable.competes4)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "AJAX Patented Load cell Weighing system", listOf(R.drawable.competes5)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Weighing system", listOf(R.drawable.competes6)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "IS 4925 :2004 Certification", listOf(R.drawable.competes7)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Fuel Consumption", listOf(R.drawable.competes8)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Drum spiral", listOf(R.drawable.competes9)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Drum", listOf(R.drawable.competes10)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Closed bucket design", listOf(R.drawable.competes11)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Loading arm", listOf(R.drawable.competes12)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Drum lifting", listOf(R.drawable.competes13)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Fuel tank", listOf(R.drawable.competes14)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Water pump", listOf(R.drawable.competes15)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Bucket gate cylinder", listOf(R.drawable.competes16)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "AJAX Vs XCMG - ROI", listOf(R.drawable.competes17)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Steering Selection", listOf(R.drawable.competes18)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Wheel dragging issue in XCMG", listOf(R.drawable.competes19)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Center of gravity", listOf(R.drawable.competes20)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Inching pedal", listOf(R.drawable.competes21)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Brake & accelerator pedal", listOf(R.drawable.competes22)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Exhaust silencer", listOf(R.drawable.competes23)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Concrete batch controller (CBC)", listOf(R.drawable.competes24)))
        itemsAjaxVsXcmg.add(ItemsModel(R.drawable.wheel_steering, "Cabin Ergonomics", listOf(R.drawable.competes25)))

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    companion object {
        fun newInstance() = CompetetionStudyFragment()
        val TAG: String = CompetetionStudyFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fab, container, false)
        viewModel = ViewModelProvider(this)[FabViewModel::class.java]
        binding.fabVModel = viewModel
        binding.recyclerView.adapter = adapter
        binding.middleList.adapter = adapterMid
        adapter.submitList(itemsAjaxVsXcmg)
        adapterMid.submitList(itemsAjaxVsXcmg[0].list)

        binding.statsGroupFab.visibility = View.GONE
        binding.middleList.visibility = View.VISIBLE
        binding.statsGroupCS.visibility = View.GONE
        binding.statsGroupCerti.visibility = View.GONE
        binding.statsGroupCompeteStudy.visibility = View.VISIBLE
        binding.pdfv.visibility = View.GONE

        binding.statsGroupCompeteStudy.setOnCheckedChangeListener(object :
            RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.ajax_vs_xcmg_menu ->{
                        adapter.submitList(itemsAjaxVsXcmg)
                        adapterMid.submitList(itemsAjaxVsXcmg[0].list)
                    }

                }
            }

        })


        return binding.root
    }


    override fun onItemClick(item: ItemsModel, position: Int) {
        Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
        adapterMid.submitList(item.list)
        adapter.notifyDataSetChanged()
        adapterMid.notifyDataSetChanged()
    }

    override fun onItemLongClick(item: ItemsModel, position: Int) {

    }

}