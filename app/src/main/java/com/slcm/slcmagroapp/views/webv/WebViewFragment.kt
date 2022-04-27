package com.slcm.slcmagroapp.views.webv

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.FragmentWebviewBinding
import com.slcm.slcmagroapp.utils.SlcmUtils


/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
class WebViewFragment : Fragment() {

    //SLCM, Product 360
    private lateinit var binding: FragmentWebviewBinding
    private lateinit var viewModel: WebViewModel
    private lateinit var ctx: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    companion object {
        fun newInstance() = WebViewFragment()
        val TAG: String = WebViewFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        viewModel = ViewModelProvider(this)[WebViewModel::class.java]
        binding.webVModel = viewModel
        setWebUrl(SlcmUtils.LOADING)

        binding.statsGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.loading_menu -> setWebUrl(SlcmUtils.LOADING)
                R.id.gear_box_motor_menu -> setWebUrl(SlcmUtils.GEAR_BOX_MOTOR)
                R.id.drum_menu -> setWebUrl(SlcmUtils.DRUM)
                R.id.mixing_menu -> setWebUrl(SlcmUtils.MIXING)
                R.id.cabin_menu -> setWebUrl(SlcmUtils.CABIN)
            }
        }

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebUrl(url: String) {

        binding.progressBar.visibility = View.VISIBLE
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }

        }
        val webSettings: WebSettings = binding.webview.settings
        webSettings.javaScriptEnabled = true
//        binding.webview.setInitialScale(140)
        binding.webview.loadUrl(url)

    }
}