package com.dimonkiv.cardscanner.ui.modules.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.main.addcategory.AddCategoryFragment
import com.dimonkiv.cardscanner.ui.modules.main.addcategory.AddCategoryPresenter
import com.dimonkiv.cardscanner.ui.modules.main.camera.CameraFragment
import com.dimonkiv.cardscanner.ui.modules.main.carddetail.CardDetailFragment
import com.dimonkiv.cardscanner.ui.modules.main.cardrecognition.CardRecognitionFragment
import com.dimonkiv.cardscanner.ui.modules.main.category.CategoryFragment
import com.dimonkiv.cardscanner.ui.modules.main.createcard.CreateCardFragment
import com.dimonkiv.cardscanner.ui.modules.main.dashboard.DashboardFragment
import com.dimonkiv.cardscanner.utill.FragmentById.*

class MainActivity : AppCompatActivity() {
    private lateinit var navigationMenu: BottomNavigationView
    private lateinit var progressBar: RelativeLayout
    private lateinit var dialogContainer: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigationMenu()
        initProgressBar()
        dialogContainer = findViewById(R.id.dialog_container)
        changeFragment(FragmentData(DASHBOARD_FRAGMENT))
    }

    fun changeFragment(fragmentData: FragmentData) {
        val fragmentById = fragmentData.getFragmentById()

        when(fragmentById) {
            DASHBOARD_FRAGMENT -> addToContainer(DashboardFragment(), null)

            CREATE_CARD_FRAGMENT -> addToContainer(CreateCardFragment(), null)

            CATEGORY_FRAGMENT -> addToContainer(CategoryFragment(), null)


            CAMERA_FRAGMENT -> addToContainer(CameraFragment(), null)

            CARD_RECOGNITION_FRAGMENT -> addToContainer(CardRecognitionFragment(), fragmentData.getBundle())

            CARD_DETAIL_FRAGMENT -> addToContainer(CardDetailFragment(), fragmentData.getBundle())

            ADD_CATEGORY_FRAGMENT -> {
                setDialogModeForDialogContainer()
                addFragmentToDialogContainer(AddCategoryFragment(), null)
            }

            BACK_FRAGMENT -> {
                setNormalModeForDialogContainer()
                onBackPressed()
            }
        }

    }

    private fun initBottomNavigationMenu() {
        navigationMenu = findViewById(R.id.navigationMenu)

        navigationMenu.setOnNavigationItemSelectedListener {
            when(it.itemId) {

                R.id.navigation_dashboard -> {
                    changeFragment(FragmentData(DASHBOARD_FRAGMENT))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_scan -> {
                    changeFragment(FragmentData(CAMERA_FRAGMENT))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_add -> {
                    changeFragment(FragmentData(CREATE_CARD_FRAGMENT))
                    return@setOnNavigationItemSelectedListener true
                }

                else -> {
                    changeFragment(FragmentData(CATEGORY_FRAGMENT))
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    private fun initProgressBar() {
        progressBar = findViewById(R.id.progress_bar_rl)
    }

    private fun addToContainer(fragment: Fragment, bundle: Bundle?) {
        if(bundle != null) fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
    }

    private fun addFragmentToDialogContainer(fragment: Fragment, bundle: Bundle?) {

        if (bundle != null) fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
                .replace(R.id.dialog_container, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun setDialogModeForDialogContainer() {
        val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        dialogContainer.layoutParams = params
    }

    private fun setNormalModeForDialogContainer() {
        val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        )
        dialogContainer.layoutParams = params
    }
}
