package com.kucingselfie.kadesubmission

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.util.gone
import com.kucingselfie.kadesubmission.util.visible
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private lateinit var navController: NavController
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = navHostFragment.findNavController()
        toolbar = findViewById(R.id.mainToolbar)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)

        initBottomNav()
    }

    private fun initBottomNav() {
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(navigationListener)
    }

    private val navigationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            invalidateOptionsMenu()
            when(destination.id) {
                R.id.listLeagueFragment -> {
                    setEnabledToolbar(false)
                }
                R.id.chooseLeagueFragment -> {
                    setEnabledToolbar(true)
                    hideToolbarBack()
                }
                R.id.favoriteMatchFragment -> {
                    setEnabledToolbar(true)
                    hideToolbarBack()
                }
            }

        }

    private fun setEnabledToolbar(isEnabled: Boolean) {
        if (isEnabled)
            appbarLayout.visible()
        else
            appbarLayout.gone()
    }

    private fun hideToolbarBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }
}
