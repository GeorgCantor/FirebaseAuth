package com.georgcantor.firebaseauth.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.georgcantor.firebaseauth.R
import com.georgcantor.firebaseauth.util.Constants.IS_LOGGED_IN
import com.georgcantor.firebaseauth.util.Constants.USER
import com.georgcantor.firebaseauth.util.PreferenceManager
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = PreferenceManager(requireContext())

        val user = manager.getUser(USER)
        fio.text = user?.fio
        birth_date.text = getString(R.string.birth_date_format, user?.birthDate)
        gender.text = getString(R.string.gender_format, user?.gender)
        email.text = getString(R.string.email_format, user?.email)

        settings.setOnClickListener {
            findNavController(this).navigate(R.id.action_profileFragment_to_regFragment)
        }

        log_out.setOnClickListener {
            manager.saveBoolean(IS_LOGGED_IN, false)
            findNavController(this).navigate(R.id.action_profileFragment_to_authPhoneFragment)
        }
    }
}