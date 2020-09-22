package com.georgcantor.firebaseauth.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.georgcantor.firebaseauth.R
import com.georgcantor.firebaseauth.util.setMaskListener
import kotlinx.android.synthetic.main.fragment_auth_phone.*

class AuthPhoneFragment : Fragment(R.layout.fragment_auth_phone) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phone_edit_text.setMaskListener(phone_input_view)
        phone_edit_text.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) phone_edit_text.hint = getString(R.string.phone_hint)
        }
    }
}