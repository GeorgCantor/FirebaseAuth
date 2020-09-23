package com.georgcantor.firebaseauth.ui.auth

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.georgcantor.firebaseauth.R
import com.georgcantor.firebaseauth.util.Constants.VERIF_ID
import com.georgcantor.firebaseauth.util.setMaskListener
import com.georgcantor.firebaseauth.util.shortToast
import com.georgcantor.firebaseauth.util.showKeyboard
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.fragment_auth_phone.*
import java.util.concurrent.TimeUnit

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
            phone_edit_text.text.toString().apply {
                if (isNotBlank()) {
                    progress_bar.visibility = VISIBLE
                    sendVerificationCode(this)
                } else {
                    phone_input_view.error = getString(R.string.input_phone)
                }
            }
        }
    }

    private fun sendVerificationCode(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, 60, TimeUnit.SECONDS, requireActivity(), call
        )
    }

    private val call = object : OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {}

        override fun onVerificationFailed(e: FirebaseException) {
            progress_bar.visibility = GONE
            phone_input_view.error = getString(R.string.wrong_phone)
            context?.shortToast(e.message)
        }

        override fun onCodeSent(verificationId: String, token: ForceResendingToken) {
            progress_bar.visibility = GONE
            findNavController(this@AuthPhoneFragment).navigate(
                R.id.action_authPhoneFragment_to_authCodeFragment,
                Bundle().apply { putString(VERIF_ID, verificationId) }
            )
        }
    }
}