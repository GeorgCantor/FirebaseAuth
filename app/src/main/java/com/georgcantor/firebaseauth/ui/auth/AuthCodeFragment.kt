package com.georgcantor.firebaseauth.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.georgcantor.firebaseauth.R
import com.georgcantor.firebaseauth.util.Constants.VERIF_ID
import com.georgcantor.firebaseauth.util.shortToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_auth_code.*

class AuthCodeFragment : Fragment(R.layout.fragment_auth_code) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verifId = arguments?.getString(VERIF_ID) ?: ""

        back_arrow.setOnClickListener { activity?.onBackPressed() }

        next_btn.setOnClickListener {
            code_edit_text.text.toString().apply {
                if (isNotBlank()) {
                    progress_bar.visibility = View.VISIBLE
                    verifyCode(verifId, this)
                } else {
                    code_input_view.error = getString(R.string.input_code)
                }
            }
        }

        code_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                code_input_view.error = null
            }
        })
    }

    private fun verifyCode(verificationId: String, code: String) {
        signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(verificationId, code))
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                when (task.isSuccessful) {
                    true -> {
                        progress_bar.visibility = GONE
                        findNavController(this).navigate(R.id.action_authCodeFragment_to_regFragment)
                    }
                    false -> {
                        progress_bar.visibility = GONE
                        code_input_view.error = getString(R.string.wrong_code)
                        context?.shortToast(task.exception?.message)
                    }
                }
            }
    }
}