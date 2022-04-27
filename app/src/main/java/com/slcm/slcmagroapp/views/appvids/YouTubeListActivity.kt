package com.slcm.slcmagroapp.views.appvids;


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.ActivityYtPlayerBinding
import com.slcm.slcmagroapp.utils.SlcmUtils
import com.slcm.slcmagroapp.views.fab.FabMenuAdapter
import com.slcm.slcmagroapp.views.fab.ItemsModel
import com.slcm.slcmagroapp.views.fab.OnFabItemClickListener

class YouTubeListActivity : YouTubeBaseActivity(), OnFabItemClickListener, View.OnClickListener {

    //SLCM, Application videos
    private lateinit var mYouTubePlayerView: YouTubePlayerView
    lateinit var mOnInitializedListener: YouTubePlayer.OnInitializedListener
    lateinit var binding: ActivityYtPlayerBinding
    lateinit var youTubePlayer: YouTubePlayer

    companion object {

        val TAG: String = YouTubeListActivity::class.java.simpleName
    }

    @RequiresApi(Build.VERSION_CODES.M)
    val adapter = FabMenuAdapter(this)

    // App Videos ArrayList
    private var itemsAPVids = mutableListOf<ItemsModel>()

    init {
        // Case study ArrayList
        val item1 = ItemsModel(R.drawable.wheel_steering, "AJAX-SMART FLEET")
        item1.urlString = "wONOP5dQZHg"

        val item2 = ItemsModel(R.drawable.wheel_steering, "AJAX-SELF LOADING CONCRETE MIXER")
        item2.urlString = "IpmO9_cU_5s"

        val item3 = ItemsModel(R.drawable.wheel_steering, "AJAX-ACURA SERIES")
        item3.urlString = "bwDoflyudw8"

        itemsAPVids.add(item1)
        itemsAPVids.add(item2)
        itemsAPVids.add(item3)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
            this@YouTubeListActivity,
            R.layout.activity_yt_player
        )

        binding.recyclerView.adapter = adapter
        adapter.submitList(itemsAPVids)

        binding.homeMenu.setOnClickListener(this)
        binding.feedbackAB.setOnClickListener(this)
        binding.full360Menu.setOnClickListener(this)
        binding.caseStudyMenu.setOnClickListener(this)
        binding.certificate.setOnClickListener(this)
        binding.calciMenu.setOnClickListener(this)
        binding.appVideosMenu.setOnClickListener(this)
//        binding.appVideosMenu.tin = applicationContext.getColor(R.color.menu_selected)

        mYouTubePlayerView = findViewById(R.id.viewYouTubePlayerView)


        mOnInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                boolean: Boolean
            ) {
                if (player != null) {
                    youTubePlayer = player
                }
                youTubePlayer.cueVideo("wONOP5dQZHg")
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                youTubeInitializationResult: YouTubeInitializationResult?
            ) {
                Log.e("TAG", "onInitializationFailure: ${youTubeInitializationResult.toString()}")
            }

        }
        mYouTubePlayerView.initialize(SlcmUtils.API_KEY, mOnInitializedListener)
    }

    private fun tubePlayer(urlStr: String) {
        youTubePlayer.loadVideo(urlStr)
    }

    override fun onItemClick(item: ItemsModel, position: Int) {
        tubePlayer(item.urlString)
        adapter.notifyDataSetChanged()
//        Log.e("TAG", "onItemClick: ${item.urlString}, $position")
    }

    override fun onItemLongClick(item: ItemsModel, position: Int) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.home_menu -> {
                setResult(1)
                finish()
            }
            R.id.calci_menu -> {
                setResult(2)
                finish()
            }
            R.id.full_360_menu -> {
                setResult(3)
                finish()
            }
            R.id.app_videos_menu -> {
                // already here setResult = 4
            }
            R.id.case_study_menu -> {
                setResult(5)
                finish()
            }
            R.id.certificate -> {
                setResult(6)
                finish()
            }
            R.id.feedback_a_b -> {
                setResult(7)
                finish()
            }
            R.id.competition_study -> {
                setResult(8)
                finish()
            }
            R.id.site_layout -> {
                setResult(9)
                finish()
            }
        }
    }

}
