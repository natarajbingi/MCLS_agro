package com.slcm.slcmagroapp.views.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slcm.slcmagroapp.MainActivity
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.FragmentHomeBinding
import com.slcm.slcmagroapp.views.appvids.YouTubeListActivity
import com.slcm.slcmagroapp.views.calci.CalciFragment
import com.slcm.slcmagroapp.views.competestudy.CompetetionStudyFragment
import com.slcm.slcmagroapp.views.cs.CSFragment
import com.slcm.slcmagroapp.views.fab.FabFragment
import com.slcm.slcmagroapp.views.pdf.PdfFragment
import com.slcm.slcmagroapp.views.webv.WebViewFragment

/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
@RequiresApi(Build.VERSION_CODES.M)
class HomeFragment : Fragment() {

    //SLCM, Agro Series
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    companion object {
        fun newInstance() = HomeFragment()
        val TAG: String = HomeFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeVModel = viewModel


        binding.fabLayout.setOnClickListener {
            MainActivity.fragmentSetter.postValue(FabFragment.TAG)
            MainActivity.fragmentMenuSetter.postValue(R.id.feedback_a_b)
        }
        binding.exploreBtn.setOnClickListener {
            MainActivity.fragmentSetter.postValue(WebViewFragment.TAG)
            MainActivity.fragmentMenuSetter.postValue(R.id.full_360_menu)
        }
        binding.caseStudyMenu.setOnClickListener {
            MainActivity.fragmentSetter.postValue(CSFragment.TAG)
            MainActivity.fragmentMenuSetter.postValue(R.id.case_study_menu)
        }
        binding.appVideosMenu.setOnClickListener {
            MainActivity.fragmentSetter.postValue(YouTubeListActivity.TAG)
//            MainActivity.fragmentMenuSetter.postValue(R.id.case_study_menu)
        }
        binding.calciMenu.setOnClickListener {
            MainActivity.fragmentSetter.postValue(CalciFragment.TAG)
            MainActivity.fragmentMenuSetter.postValue(R.id.calci_menu)
        }
        binding.certificate.setOnClickListener {
            MainActivity.fragmentSetter.postValue(PdfFragment.TAG)
            MainActivity.fragmentMenuSetter.postValue(R.id.certificate)
        }
        binding.competitionStudyMenu.setOnClickListener {
            MainActivity.fragmentSetter.postValue(CompetetionStudyFragment.TAG)
            MainActivity.fragmentMenuSetter.postValue(R.id.competition_study)
        }


        return binding.root
    }

}