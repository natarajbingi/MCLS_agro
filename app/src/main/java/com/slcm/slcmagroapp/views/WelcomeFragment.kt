package com.slcm.slcmagroapp.views

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.slcm.slcmagroapp.MainActivity
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.FragmentWelcomeBinding
import com.slcm.slcmagroapp.views.home.HomeFragment
import com.slcm.slcmagroapp.views.pdf.PdfViewModel


@RequiresApi(Build.VERSION_CODES.M)
class WelcomeFragment : Fragment() {

    lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: PdfViewModel
    lateinit var exoPlayer: ExoPlayer

    companion object {
        fun newInstance() = WelcomeFragment()
        val TAG: String = WelcomeFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        viewModel = ViewModelProvider(this)[PdfViewModel::class.java]
        binding.modelPlayer = viewModel
        // Inflate the layout for this fragment

        exoPlayer = ExoPlayer.Builder(requireContext())
            .setSeekBackIncrementMs(5000)
            .setSeekForwardIncrementMs(5000)
            .build()
        binding.playerWelcome.player = exoPlayer
        binding.playerWelcome.keepScreenOn = true
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_BUFFERING) {
                    binding.progressBar.visibility = View.VISIBLE
                } else if (playbackState == Player.STATE_READY) {
                    binding.progressBar.visibility = View.GONE
                } else if (playbackState == Player.STATE_ENDED) {
                    MainActivity.fragmentSetter.postValue(HomeFragment.TAG)
                }

            }
            /*override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_BUFFERING) {
                    binding.progressBar.visibility = View.VISIBLE
                } else if (playbackState == Player.STATE_READY) {
                    binding.progressBar.visibility = View.GONE
                } else if (playbackState == Player.STATE_ENDED) {
                    MainActivity.fragmentSetter.postValue(HomeFragment.TAG)
                }
            }*/
        })

        val videoPath = RawResourceDataSource.buildRawResourceUri(R.raw.welcome_screen).toString()
        val uri: Uri = RawResourceDataSource.buildRawResourceUri(R.raw.welcome_screen)

        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()

        binding.skipHere.setOnClickListener {
            MainActivity.fragmentSetter.postValue(HomeFragment.TAG)
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
            exoPlayer.stop()
        }
    }
}