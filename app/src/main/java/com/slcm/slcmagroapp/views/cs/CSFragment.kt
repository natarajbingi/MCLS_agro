package com.slcm.slcmagroapp.views.cs

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.FragmentFabBinding
import com.slcm.slcmagroapp.views.fab.FabMenuAdapter
import com.slcm.slcmagroapp.views.fab.FabViewModel
import com.slcm.slcmagroapp.views.fab.ItemsModel
import com.slcm.slcmagroapp.views.fab.OnFabItemClickListener

/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
@RequiresApi(Build.VERSION_CODES.M)
class CSFragment : Fragment(), OnFabItemClickListener {

    companion object {
        fun newInstance() = CSFragment()
        val TAG: String = CSFragment::class.java.simpleName
    }

    //SLCM, Case Study
    private lateinit var binding: FragmentFabBinding
    private lateinit var viewModel: FabViewModel
    private lateinit var ctx: Context
    private val adapter = FabMenuAdapter(this)
    private val adapterMid = MidImgAdapter(this)

    // Case study ArrayList
    private var itemsAPMC = mutableListOf<ItemsModel>()
    private var itemsArgo2000inCCRoads = mutableListOf<ItemsModel>()
    private var itemsPMAY = mutableListOf<ItemsModel>()
    private var itemsPrecast = mutableListOf<ItemsModel>()
    private var itemsSiteLayOuting = mutableListOf<ItemsModel>()
    private var itemsUrbanInfrStr = mutableListOf<ItemsModel>()


    init {

        // Case study ArrayList
        itemsAPMC.add(ItemsModel(R.drawable.wheel_steering, "Application summary", listOf(R.drawable.apmc_application_summary)))
        itemsAPMC.add(ItemsModel(R.drawable.wheel_steering, "Project Synopsis", listOf(R.drawable.apmc_project_synopsis)))
        itemsAPMC.add(ItemsModel(R.drawable.wheel_steering, "Concrete Requirement", listOf(R.drawable.apmc_concrete_requirement,R.drawable.apmc_concrete_requirement1)))
        itemsAPMC.add(ItemsModel(R.drawable.wheel_steering, "Mix Design", listOf(R.drawable.apmc_mix_design)))
        itemsAPMC.add(ItemsModel(R.drawable.wheel_steering, "Site Layout", listOf(R.drawable.apmc_work_in_progress_site_images)))
        itemsAPMC.add(ItemsModel(R.drawable.wheel_steering, "Cycle Time Analysis", listOf(R.drawable.apmc_cycle_time_analysis)))
        itemsAPMC.add(ItemsModel(R.drawable.wheel_steering, "Machine Performance", listOf(R.drawable.apmc_machine_performance)))
        itemsAPMC.add(ItemsModel(R.drawable.wheel_steering, "Work in Progress - Site Images", listOf(R.drawable.apmc_work_in_progress_site_images)))

        itemsArgo2000inCCRoads.add(ItemsModel(R.drawable.wheel_steering, "Application summary", listOf(R.drawable.ccroads_application_summary)))
        itemsArgo2000inCCRoads.add(ItemsModel(R.drawable.wheel_steering, "Project Synopsis", listOf(R.drawable.ccroads_project_synopsis)))
        itemsArgo2000inCCRoads.add(ItemsModel(R.drawable.wheel_steering, "Concrete Requirement", listOf(R.drawable.ccroads_concrete_requirement)))
        itemsArgo2000inCCRoads.add(ItemsModel(R.drawable.wheel_steering, "Site Layout diagram", listOf(R.drawable.ccroads_site_layout_diagram)))
        itemsArgo2000inCCRoads.add(ItemsModel(R.drawable.wheel_steering, "Cycle Time Analysis", listOf(R.drawable.ccroads_cycle_time_analysis)))
        itemsArgo2000inCCRoads.add(ItemsModel(R.drawable.wheel_steering, "Machine Performance", listOf(R.drawable.ccroads_machine_performance)))
        itemsArgo2000inCCRoads.add(ItemsModel(R.drawable.wheel_steering, "Voice of Customer", listOf(R.drawable.ccroads_voice_of_customer)))
//        itemsArgo2000inCCRoads.add(ItemsModel(R.drawable.wheel_steering, "Work in Progress - Site Images", listOf(R.drawable.ccroads_w)))

        itemsPMAY.add(ItemsModel(R.drawable.wheel_steering, "Application Summary", listOf(R.drawable.pmay_application_summary)))
        itemsPMAY.add(ItemsModel(R.drawable.wheel_steering, "Project Synopsis", listOf(R.drawable.pmay_project_synopsis)))
        itemsPMAY.add(ItemsModel(R.drawable.wheel_steering, "Model House Plan", listOf(R.drawable.pmay_model_house_plan)))
        itemsPMAY.add(ItemsModel(R.drawable.wheel_steering, "Concrete Requirement", listOf(R.drawable.pmay_concrete_requirement)))
        itemsPMAY.add(ItemsModel(R.drawable.wheel_steering, "Site Layout", listOf(R.drawable.pmay_site_layout)))
        itemsPMAY.add(ItemsModel(R.drawable.wheel_steering, "Advantage of ARGO", listOf(R.drawable.pmay_advantage_of_argo)))
        itemsPMAY.add(ItemsModel(R.drawable.wheel_steering, "Voice of Customer", listOf(R.drawable.pmay_voice_of_customer)))
        itemsPMAY.add(ItemsModel(R.drawable.wheel_steering, "Work in Progress - Site Images", listOf(R.drawable.pmay_work_in_progress_site_images)))

        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Application summary", listOf(R.drawable.precast_application_summary)))
        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Project Synopsis", listOf(R.drawable.precast_project_synopsis)))
        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Precast Designs", listOf(R.drawable.precast_precast_designs)))
        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Concrete Requirement", listOf(R.drawable.precast_concrete_requirement)))
        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Site Layout", listOf(R.drawable.precast_site_layout)))
        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Cycle Time Analysis", listOf(R.drawable.precast_cycle_time_analysis)))
        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Machine Performance", listOf(R.drawable.precast_machine_performance)))
        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Work in Progress – Concreting Cycle", listOf(R.drawable.precast_work_in_progress_concreting_cycle)))
        itemsPrecast.add(ItemsModel(R.drawable.wheel_steering, "Work in Progress - Site Images", listOf(R.drawable.precast_work_in_progress_site_images)))


        itemsSiteLayOuting.add(ItemsModel(R.drawable.wheel_steering, "Application summary", listOf(R.drawable.site_layouting_application_summary)))
        itemsSiteLayOuting.add(ItemsModel(R.drawable.wheel_steering, "Project Synopsis", listOf(R.drawable.site_layouting_project_synopsis)))
        itemsSiteLayOuting.add(ItemsModel(R.drawable.wheel_steering, "Structure Dimensions", listOf(R.drawable.site_layouting_structure_dimensions)))
        itemsSiteLayOuting.add(ItemsModel(R.drawable.wheel_steering, "Concrete Requirement", listOf(R.drawable.site_layouting_concrete_requirement,R.drawable.site_layouting_concrete_requirement1)))
        itemsSiteLayOuting.add(ItemsModel(R.drawable.wheel_steering, "Site Layout", listOf(R.drawable.site_layouting_site_layout)))
        itemsSiteLayOuting.add(ItemsModel(R.drawable.wheel_steering, "Cycle Time Analysis", listOf(R.drawable.site_layouting_cycle_time_analysis)))
        itemsSiteLayOuting.add(ItemsModel(R.drawable.wheel_steering, "Machine Performance", listOf(R.drawable.site_layouting_machine_performance)))
        itemsSiteLayOuting.add(ItemsModel(R.drawable.wheel_steering, "Work in Progress - Site Images", listOf(R.drawable.site_layouting_work_in_progress_site_images)))


        itemsUrbanInfrStr.add(ItemsModel(R.drawable.wheel_steering, "Application summary", listOf(R.drawable.ui_application_summary)))
        itemsUrbanInfrStr.add(ItemsModel(R.drawable.wheel_steering, "Project Synopsis", listOf(R.drawable.ui_project_synopsis)))
        itemsUrbanInfrStr.add(ItemsModel(R.drawable.wheel_steering, "Structure Dimensions", listOf(R.drawable.ui_structure_dimensions)))
        itemsUrbanInfrStr.add(ItemsModel(R.drawable.wheel_steering, "Concrete Requirement", listOf(R.drawable.ui_concrete_requirement)))
        itemsUrbanInfrStr.add(ItemsModel(R.drawable.wheel_steering, "Site Layout", listOf(R.drawable.ui_site_layout)))
        itemsUrbanInfrStr.add(ItemsModel(R.drawable.wheel_steering, "Cycle Time Analysis – ARGO 4300", listOf(R.drawable.ui_cycle_time_analysisargo4300,R.drawable.ui_cycle_time_analysis_argo_4300)))
        itemsUrbanInfrStr.add(ItemsModel(R.drawable.wheel_steering, "Machine Performance", listOf(R.drawable.ui_machine_performance)))
        itemsUrbanInfrStr.add(ItemsModel(R.drawable.wheel_steering, "Work in Progress - Site Images", listOf(R.drawable.ui_work_in_progress_site_images,R.drawable.ui_work_in_progress_site_images1)))

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
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

        adapter.submitList(itemsAPMC)
        adapterMid.submitList(itemsAPMC[0].list)

        binding.statsGroupCS.visibility = View.VISIBLE
        binding.middleList.visibility = View.VISIBLE
        binding.statsGroupFab.visibility = View.GONE
        binding.statsGroupCerti.visibility = View.GONE
        binding.pdfv.visibility = View.GONE
        binding.statsGroupCompeteStudy.visibility = View.GONE
        //binding.mainImage.setImageDrawable(ctx.getDrawable(R.drawable.appli_summry))

        binding.statsGroupCS.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.apmc_menu -> {
                    adapter.submitList(itemsAPMC)
                    adapterMid.submitList(itemsAPMC[0].list)
                }
                R.id.argo2000inCCRoads_menu -> {
                    adapter.submitList(itemsArgo2000inCCRoads)
                    adapterMid.submitList(itemsArgo2000inCCRoads[0].list)
                }
                R.id.pMay_menu -> {
                    adapter.submitList(itemsPMAY)
                    adapterMid.submitList(itemsPMAY[0].list)
                }
                R.id.Precast_menu -> {
                    adapter.submitList(itemsPrecast)
                    adapterMid.submitList(itemsPrecast[0].list)
                }
                R.id.Site_Layouting_menu -> {
                    adapter.submitList(itemsSiteLayOuting)
                    adapterMid.submitList(itemsSiteLayOuting[0].list)
                }
                R.id.URBAN_INFRASTRUCTURE_menu -> {
                    adapter.submitList(itemsUrbanInfrStr)
                    adapterMid.submitList(itemsUrbanInfrStr[0].list)
                }
            }
        }

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