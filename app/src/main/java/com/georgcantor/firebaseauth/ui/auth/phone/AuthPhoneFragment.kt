package com.georgcantor.firebaseauth.ui.auth.phone

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.georgcantor.firebaseauth.R
import com.georgcantor.firebaseauth.util.setMaskListener
import com.georgcantor.firebaseauth.util.showKeyboard
import kotlinx.android.synthetic.main.fragment_auth_phone.*

class AuthPhoneFragment : Fragment(R.layout.fragment_auth_phone) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(phone_edit_text) {
            setMaskListener(phone_input_view)
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    hint = getString(R.string.phone_hint)
                    activity?.showKeyboard()
                }
            }
        }

        next_btn.setOnClickListener {
            findNavController(this).navigate(R.id.action_authPhoneFragment_to_authCodeFragment)
        }
    }
}