package com.slcm.slcmagroapp.views.appvids

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
import com.google.android.youtube.player.YouTubePlayer
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
class AppVidsFragment : Fragment(), OnFabItemClickListener {

    private lateinit var binding: FragmentFabBinding
    private lateinit var viewModel: FabViewModel
    private lateinit var ctx: Context
    lateinit var initializer: YouTubePlayer.OnInitializedListener
    private val adapter = FabMenuAdapter(this)

    // App Videos ArrayList
    private var itemsAPVids = mutableListOf<ItemsModel>()

    init {
        // Case study ArrayList
        itemsAPVids.add(ItemsModel(R.drawable.wheel_steering, "Building Construction"))
        itemsAPVids.add(ItemsModel(R.drawable.wheel_steering, "Irrigation"))
        itemsAPVids.add(ItemsModel(R.drawable.wheel_steering, "Railways"))
        itemsAPVids.add(ItemsModel(R.drawable.wheel_steering, "Roads"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    companion object {
        fun newInstance() = AppVidsFragment()
        val TAG: String = AppVidsFragment::class.java.simpleName
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
        adapter.submitList(itemsAPVids)

        binding.statsGroupFab.visibility = View.GONE
        binding.statsGroupCS.visibility = View.GONE
//        binding.mainImage.visibility = View.GONE



        return binding.root
    }


    override fun onItemClick(item: ItemsModel, position: Int) {
        Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
    }

    override fun onItemLongClick(item: ItemsModel, position: Int) {

    }

}