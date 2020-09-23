package com.georgcantor.firebaseauth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.georgcantor.firebaseauth.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.isLoggedIn.observe(this) { loggedIn ->
            val navHostFragment = nav_host_fragment as NavHostFragment
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.nav_graph)

            graph.startDestination = if (loggedIn) R.id.profileFragment else R.id.authPhoneFragment

            navHostFragment.navController.graph = graph
        }
    }
}