package com.slcm.slcmagroapp.views.calci

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.FragmentCalciBinding
import com.slcm.slcmagroapp.utils.SlcmUtils
import kotlin.math.round


/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
class CalciFragment : Fragment(), OnCalciItemClickListener {


    //SLCM, Agro Calculator
    private lateinit var binding: FragmentCalciBinding
    private lateinit var viewModel: CalciViewModel
    private lateinit var ctx: Context

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    val adapter = CalciAdapter(this@CalciFragment)

    // ArrayList
    private var itemsArgoReqs = mutableListOf<CalciItemsModel>()

    private var avgWorkingDayMonth = 25
    private val noOfConcretingDaysMonth = 18
    private var avgWorkingHrsDay = 8


    init {
        itemsArgoReqs.add(CalciItemsModel(title = "2000", roundUpHrs = "0", Hrs = "0.0"))
        itemsArgoReqs.add(CalciItemsModel(title = "2300", roundUpHrs = "0", Hrs = "0.0"))
        itemsArgoReqs.add(CalciItemsModel(title = "2800", roundUpHrs = "0", Hrs = "0.0"))
        itemsArgoReqs.add(CalciItemsModel(title = "4300", roundUpHrs = "0", Hrs = "0.0"))
        itemsArgoReqs.add(CalciItemsModel(title = "4800", roundUpHrs = "0", Hrs = "0.0"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    companion object {
        fun newInstance() = CalciFragment()
        val TAG: String = CalciFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calci, container, false)
        viewModel = ViewModelProvider(this)[CalciViewModel::class.java]
        binding.calciModel = viewModel
        binding.recyclerView.adapter = adapter
        adapter.submitList(itemsArgoReqs)

        binding.monthlyConcreteProductionLayout.setOnClickListener {
            calcIt()
        }

        closeKeyBoard(binding.customerPreferredArgoSpinner)
        closeKeyBoard(binding.pjtCategorySpinner)
        closeKeyBoard(binding.customerTypeSpinner)
        closeKeyBoard(binding.pjtSubCatSpinner)

        return binding.root
    }

    private fun closeKeyBoard(mySpinner:Spinner){
        mySpinner.setOnTouchListener(OnTouchListener { v, event ->
            val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mySpinner.windowToken, 0)
        })
    }

    private fun calcIt() {
        val concreteQty = (binding.concretingRequirementM3Text.text.toString())
        val durationOfConcretingMonths = (binding.concretingRequirementText.text.toString())
        var durationOfProjectMonths = (binding.concretingRequirementText.text.toString())
        val avgCycleTimeText = (binding.avgCycleTimeText.text.toString())
        var avgConcreteRequirementPerDay = 0.0

        if (concreteQty.isNotEmpty() && durationOfConcretingMonths.isNotEmpty()) {
            avgConcreteRequirementPerDay =
                (concreteQty.toDouble() / (durationOfConcretingMonths.toDouble() * noOfConcretingDaysMonth))
            val monthlyConcreteProduction =
                concreteQty.toDouble() / durationOfConcretingMonths.toDouble()
            binding.monthlyConcreteProductionText.text = round(monthlyConcreteProduction).toString()
        }

        val prefArgoString: String = binding.customerPreferredArgoSpinner.selectedItem.toString()
        if (prefArgoString != "Select") {
            itemsArgoReqs.clear()
            val prefArgo = prefArgoString.toInt()
            Log.e(TAG, "prefArgoString: $prefArgoString")

            val cycleTime2000 = less4000(prefArgo, avgCycleTimeText.toDouble())
            val concretePerHour2000 = (60 / cycleTime2000) * 2
            Log.e(TAG, "cycleTime2000: $cycleTime2000 - $concretePerHour2000")

            val cycleTime2300 = less4000(prefArgo, avgCycleTimeText.toDouble())
            val concretePerHour2300 = (60 / cycleTime2300) * 2.3
            Log.e(TAG, "cycleTime2300: $cycleTime2300 - $concretePerHour2300")

            val cycleTime2800 = less4000(prefArgo, avgCycleTimeText.toDouble())
            val concretePerHour2800 = (60 / cycleTime2800) * 2.8
            Log.e(TAG, "cycleTime2800: $cycleTime2800 - $concretePerHour2800")

            val cycleTime4300 = greater4000(prefArgo, avgCycleTimeText.toDouble())
            val concretePerHour4300 = (60 / cycleTime4300) * 4.3
            Log.e(TAG, "cycleTime4300: $cycleTime4300 - $concretePerHour4300")

            val cycleTime4800 = greater4000(prefArgo, avgCycleTimeText.toDouble())
            val concretePerHour4800 = (60 / cycleTime4800) * 4.8
            Log.e(TAG, "cycleTime4800: $cycleTime4800 - $concretePerHour4800")

            val upHrs2000 = avgConcreteRequirementPerDay / (concretePerHour2000 * avgWorkingHrsDay)
            val upHrs2300 = avgConcreteRequirementPerDay / (concretePerHour2300 * avgWorkingHrsDay)
            val upHrs2800 = avgConcreteRequirementPerDay / (concretePerHour2800 * avgWorkingHrsDay)
            val upHrs4300 = avgConcreteRequirementPerDay / (concretePerHour4300 * avgWorkingHrsDay)
            val upHrs4800 = avgConcreteRequirementPerDay / (concretePerHour4800 * avgWorkingHrsDay)

            Log.e(TAG, "avgConcreteRequirementPerDay: $avgConcreteRequirementPerDay")

            itemsArgoReqs.add(
                CalciItemsModel(
                    title = "2000",
                    roundUpHrs = "${round(upHrs2000)}",
                    Hrs = "$upHrs2000"
                )
            )
            itemsArgoReqs.add(
                CalciItemsModel(
                    title = "2300",
                    roundUpHrs = "${round(upHrs2300)}",
                    Hrs = "$upHrs2300"
                )
            )
            itemsArgoReqs.add(
                CalciItemsModel(
                    title = "2800",
                    roundUpHrs = "${round(upHrs2800)}",
                    Hrs = "$upHrs2800"
                )
            )
            itemsArgoReqs.add(
                CalciItemsModel(
                    title = "4300",
                    roundUpHrs = "${round(upHrs4300)}",
                    Hrs = "$upHrs4300"
                )
            )
            itemsArgoReqs.add(
                CalciItemsModel(
                    title = "4800",
                    roundUpHrs = "${round(upHrs4800)}",
                    Hrs = "$upHrs4800"
                )
            )

            adapter.notifyDataSetChanged()
        } else {
            context?.let {
                SlcmUtils.toastIt(
                    it,
                    "Please select Custom preferred Argo to continue"
                )
            }
        }
    }

    private fun less4000(prefArgo: Int, avgCycleTimeSpinner: Double): Double {
        return when {
            prefArgo < 4000 -> {
                avgCycleTimeSpinner
            }
            else -> {
                avgCycleTimeSpinner - (avgCycleTimeSpinner * 0.2)
            }
        }
    }

    private fun greater4000(prefArgo: Int, avgCycleTimeSpinner: Double): Double {
        return when {
            prefArgo >= 4000 -> {
                avgCycleTimeSpinner
            }
            else -> {
                avgCycleTimeSpinner + (avgCycleTimeSpinner * 0.2)
            }
        }
    }

    override fun onItemClick(item: CalciItemsModel, position: Int) {}

    override fun onItemLongClick(item: CalciItemsModel, position: Int) {}

}