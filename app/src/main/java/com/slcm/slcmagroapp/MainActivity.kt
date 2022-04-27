package com.slcm.slcmagroapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.slcm.slcmagroapp.databinding.ActivityMainBinding
import com.slcm.slcmagroapp.utils.SlcmUtils.setTint
import com.slcm.slcmagroapp.views.WelcomeFragment
import com.slcm.slcmagroapp.views.appvids.YouTubeListActivity
import com.slcm.slcmagroapp.views.calci.CalciFragment
import com.slcm.slcmagroapp.views.competestudy.CompetetionStudyFragment
import com.slcm.slcmagroapp.views.cs.CSFragment
import com.slcm.slcmagroapp.views.fab.FabFragment
import com.slcm.slcmagroapp.views.home.HomeFragment
import com.slcm.slcmagroapp.views.pdf.PdfFragment
import com.slcm.slcmagroapp.views.webv.WebViewFragment

/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    var fragmentTag: String = ""

    companion object {
        val fragmentSetter: MutableLiveData<String> = MutableLiveData()
        val fragmentMenuSetter: MutableLiveData<Int> = MutableLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        fragmentTag = WelcomeFragment.TAG
        replaceFragment(HomeFragment.TAG)

        binding.homeMenu.setOnClickListener(this)
        binding.feedbackAB.setOnClickListener(this)
        binding.full360Menu.setOnClickListener(this)
        binding.caseStudyMenu.setOnClickListener(this)
        binding.certificate.setOnClickListener(this)
        binding.calciMenu.setOnClickListener(this)
        binding.appVideosMenu.setOnClickListener(this)
        binding.siteLayout.setOnClickListener(this)
        binding.competitionStudy.setOnClickListener(this)


        fragmentSetter.observe(this) {
            if (fragmentTag != it) {
                replaceFragment(it)
            }
        }
        fragmentMenuSetter.observe(this) {
            setActiveMenu(it)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.home_menu -> {
                replaceFragment(HomeFragment.TAG)
            }
            R.id.calci_menu -> {
                replaceFragment(CalciFragment.TAG)
                //Toast.makeText(applicationContext, "Under Progress", Toast.LENGTH_LONG).show()
            }
            R.id.full_360_menu -> {
                replaceFragment(WebViewFragment.TAG)// 360 full menu
            }
            R.id.app_videos_menu -> {
                replaceFragment(YouTubeListActivity.TAG)
                return
            }
            R.id.certificate -> {
                replaceFragment(PdfFragment.TAG)
            }
            R.id.feedback_a_b -> {
                replaceFragment(FabFragment.TAG)
            }
            R.id.case_study_menu -> {
                replaceFragment(CSFragment.TAG)
            }
            R.id.competition_study -> {
                replaceFragment(CompetetionStudyFragment.TAG)
            }
            R.id.site_layout -> {
//                replaceFragment(PdfFragment.TAG)
                Toast.makeText(applicationContext, "site_layout Progress", Toast.LENGTH_LONG).show()
                return
            }
        }
        v?.id?.let { setActiveMenu(it) }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            supportFragmentManager.popBackStack()
            super.onBackPressed();

        } else if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
    }

    private val regContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                1 -> {
                    replaceFragment(HomeFragment.TAG)
                    setActiveMenu(R.id.home_menu)
                }
                2 -> {
                    replaceFragment(CalciFragment.TAG) // calculator
                    setActiveMenu(R.id.calci_menu)
                }
                3 -> {
                    replaceFragment(WebViewFragment.TAG) // 360 full menu
                    setActiveMenu(R.id.full_360_menu)
                }
                /*4 -> {
                    replaceFragment(HomeFragment.TAG) // default home screen if 4 passed
                }*/
                5 -> {
                    replaceFragment(CSFragment.TAG)  // Case Study
                    setActiveMenu(R.id.case_study_menu)
                }
                6 -> {
                    replaceFragment(PdfFragment.TAG) // Certificate & Report
                    setActiveMenu(R.id.certificate)
                }
                7 -> {
                    replaceFragment(FabFragment.TAG) // Feature,Advantage & Benefits
                    setActiveMenu(R.id.feedback_a_b)
                }
                8 -> {
                    replaceFragment(CompetetionStudyFragment.TAG) // Competition Study
                    setActiveMenu(R.id.competition_study)
                }
                9 -> {
                    //   replaceFragment(SiteLayoutFragment.TAG) // yet to create Site Layout
                    Toast.makeText(applicationContext, "site_layout Progress", Toast.LENGTH_LONG).show()
                }
            }
        }

    // Extension function to replace fragment
    private fun replaceFragment(fragmentTag: String) {
        if (YouTubeListActivity.TAG == fragmentTag) {
            val intent = Intent(this@MainActivity, YouTubeListActivity::class.java)
            regContract.launch(intent)
            return
        }
        val fragment: Fragment = when (fragmentTag) {
            HomeFragment.TAG -> {
                binding.fragMenuTitle.text = "SLCM, Agro Series"
                HomeFragment.newInstance()
            }
            FabFragment.TAG -> {
                binding.fragMenuTitle.text = "SLCM, Feature. Advantage. Benefits"
                FabFragment.newInstance()
            }
            WebViewFragment.TAG -> {
                binding.fragMenuTitle.text = "SLCM, Product 360"
                WebViewFragment.newInstance()
            }
            CSFragment.TAG -> {
                binding.fragMenuTitle.text = "SLCM, Case Study"
                CSFragment.newInstance()
            }
            CalciFragment.TAG -> {
                binding.fragMenuTitle.text = "SLCM, Agro Calculator"
                CalciFragment.newInstance()
            }
            PdfFragment.TAG -> { // Certification & Reports
                binding.fragMenuTitle.text = "SLCM, Certification & Reports"
                PdfFragment.newInstance()
            }
            WelcomeFragment.TAG -> {
                binding.fragMenuTitle.text = "SLCM, Welcome"
                WelcomeFragment.newInstance()
            }
            CompetetionStudyFragment.TAG -> {
                binding.fragMenuTitle.text = "SLCM, Competition Study"
                CompetetionStudyFragment.newInstance()
            }
            else -> {
                this.fragmentTag = HomeFragment.TAG
                binding.fragMenuTitle.text = "SLCM, Agro Series"
                HomeFragment.newInstance()
            }
        }
        if (this.fragmentTag != fragmentTag) {
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.host, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
            this.fragmentTag = fragmentTag
        }
    }

    private fun setActiveMenu(id: Int) {
        binding.homeMenu.setTint(R.color.menu_inactive)
        binding.calciMenu.setTint(R.color.menu_inactive)
        binding.full360Menu.setTint(R.color.menu_inactive)
        binding.appVideosMenu.setTint(R.color.menu_inactive)
        binding.caseStudyMenu.setTint(R.color.menu_inactive)
        binding.certificate.setTint(R.color.menu_inactive)
        binding.feedbackAB.setTint(R.color.menu_inactive)
        binding.competitionStudy.setTint(R.color.menu_inactive)
        binding.siteLayout.setTint(R.color.menu_inactive)

        findViewById<ImageView>(id).setTint(R.color.menu_text_selected)
    }
}
