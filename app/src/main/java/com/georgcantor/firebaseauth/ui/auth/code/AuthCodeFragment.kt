package com.georgcantor.firebaseauth.ui.auth.code

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.georgcantor.firebaseauth.R
import kotlinx.android.synthetic.main.fragment_auth_code.*

class AuthCodeFragment : Fragment(R.layout.fragment_auth_code) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_arrow.setOnClickListener { activity?.onBackPressed() }
    }
}