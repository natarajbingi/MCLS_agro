package com.slcm.slcmagroapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.slcm.slcmagroapp.databinding.FragmentWelcomeBinding

@RequiresApi(Build.VERSION_CODES.M)
class SplashActivity : AppCompatActivity() {

    lateinit var binding: FragmentWelcomeBinding
    lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SplashActivity, R.layout.fragment_welcome)

        exoPlayer = ExoPlayer.Builder(this)
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
                    launchMainActivity()
                }

            }
        })

        val uri: Uri = RawResourceDataSource.buildRawResourceUri(R.raw.welcome_screen)

        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()

        binding.skipHere.setOnClickListener {
            launchMainActivity()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
            exoPlayer.stop()
        }
    }

    private fun launchMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}