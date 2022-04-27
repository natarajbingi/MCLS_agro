package com.slcm.slcmagroapp.views.fab

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

/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
@RequiresApi(Build.VERSION_CODES.M)
class FabFragment : Fragment(), OnFabItemClickListener {

    private lateinit var binding: FragmentFabBinding
    private lateinit var viewModel: FabViewModel
    private lateinit var ctx: Context
    val adapter = FabMenuAdapter(this)
    private val adapterMid = MidImgAdapter(this)

    //    FAB Menu ArrayList SLCM, Feature. Advantage. Benefits
    private var itemsMobility = mutableListOf<ItemsModel>()
    private var itemsEaseOfOperation = mutableListOf<ItemsModel>()
    private var itemsConcreting = mutableListOf<ItemsModel>()
    private var itemsPlacingConcrete = mutableListOf<ItemsModel>()
    private var itemsSafetyMnt = mutableListOf<ItemsModel>()

    init {
        itemsMobility.add(ItemsModel(R.drawable.wheel_steering, "Wheel Steering", listOf(R.drawable.wheel_steering_mid)))
        itemsMobility.add(ItemsModel(R.drawable.wheel_drive_4, "4 Wheel Drive", listOf(R.drawable.wheel_drive_4_mid)))
        itemsMobility.add(ItemsModel(R.drawable.peddle_4_inch, "4 Inch Peddle", listOf(R.drawable.peddle_4_inch_mid)))

        itemsEaseOfOperation.add(ItemsModel(R.drawable.peddle_4_inch, "Ergonomic cabin", listOf(R.drawable.ergonomic_cabin)))
        itemsEaseOfOperation.add(ItemsModel(R.drawable.wheel_drive_4, "Adjustable Operator's seat", listOf(R.drawable.adjustable_operator_seat)))
        itemsEaseOfOperation.add(ItemsModel(R.drawable.wheel_steering, "Joystick operations", listOf(R.drawable.joystick_operations)))

        itemsConcreting.add(ItemsModel(R.drawable.wheel_steering, "High wear resistant steel", listOf(R.drawable.high_wear_resistant_steel)))
        itemsConcreting.add(ItemsModel(R.drawable.wheel_steering, "Electronic Drum Control unit", listOf(R.drawable.electronic_rum_control_unit)))
        itemsConcreting.add(ItemsModel(R.drawable.wheel_steering, "Load cell weighing system", listOf(R.drawable.load_cell_weighing_system)))
        itemsConcreting.add(ItemsModel(R.drawable.wheel_steering, "Smart concrete Batch controller", listOf(R.drawable.smart_concrete_batch_controller)))
        itemsConcreting.add(ItemsModel(R.drawable.wheel_steering, "Auto water cut-off", listOf(R.drawable.auto_water_cut_off)))
        itemsConcreting.add(ItemsModel(R.drawable.wheel_steering, "Water Tank", listOf(R.drawable.water_tank)))

        itemsPlacingConcrete.add(ItemsModel(R.drawable.wheel_steering, "Drum Lift", listOf(R.drawable.drum_lift)))
        itemsPlacingConcrete.add(ItemsModel(R.drawable.wheel_steering, "Discharge Chute", listOf(R.drawable.discharge_chute)))

        itemsSafetyMnt.add(ItemsModel(R.drawable.wheel_steering, "Parking Brake system", listOf(R.drawable.parking_break_system)))
        itemsSafetyMnt.add(ItemsModel(R.drawable.wheel_steering, "Inching peddle", listOf(R.drawable.inching_peddle)))
        itemsSafetyMnt.add(ItemsModel(R.drawable.wheel_steering, "Auto cleaning system", listOf(R.drawable.auto_cleaning_system)))
        itemsSafetyMnt.add(ItemsModel(R.drawable.wheel_steering, "High pressure water jet", listOf(R.drawable.high_pressure_water_jet)))

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    companion object {
        fun newInstance() = FabFragment()
        val TAG: String = FabFragment::class.java.simpleName
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
        adapter.submitList(itemsMobility)
        adapterMid.submitList(itemsMobility[0].list)

        binding.statsGroupFab.visibility = View.VISIBLE
        binding.middleList.visibility = View.VISIBLE
        binding.statsGroupCS.visibility = View.GONE
        binding.statsGroupCerti.visibility = View.GONE
        binding.pdfv.visibility = View.GONE
        binding.statsGroupCompeteStudy.visibility = View.GONE

        binding.statsGroupFab.setOnCheckedChangeListener(object :
            RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.mobility_menu ->{
                        adapter.submitList(itemsMobility)
                        adapterMid.submitList(itemsMobility[0].list)
                    }
                    R.id.easeOfOperation_menu -> {
                        adapter.submitList(itemsEaseOfOperation)
                        adapterMid.submitList(itemsEaseOfOperation[0].list)
                    }
                    R.id.concreting_menu -> {
                        adapter.submitList(itemsConcreting)
                        adapterMid.submitList(itemsConcreting[0].list)
                    }
                    R.id.placingConcrete_menu -> {
                        adapter.submitList(itemsPlacingConcrete)
                        adapterMid.submitList(itemsPlacingConcrete[0].list)
                    }
                    R.id.safety_menu -> {
                        adapter.submitList(itemsSafetyMnt)
                        adapterMid.submitList(itemsSafetyMnt[0].list)
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