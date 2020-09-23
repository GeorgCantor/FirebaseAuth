package com.georgcantor.firebaseauth.ui.reg

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.georgcantor.firebaseauth.R
import com.georgcantor.firebaseauth.data.User
import com.georgcantor.firebaseauth.util.Constants.IS_LOGGED_IN
import com.georgcantor.firebaseauth.util.Constants.USER
import com.georgcantor.firebaseauth.util.PreferenceManager
import com.georgcantor.firebaseauth.util.shortToast
import kotlinx.android.synthetic.main.fragment_reg.*

class RegFragment : Fragment(R.layout.fragment_reg) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_arrow.setOnClickListener { activity?.onBackPressed() }

        val genders = resources.getStringArray(R.array.genders)

        gender_spinner.apply {
            adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                genders
            )

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    (view as TextView).text = null
                    gender_edit_text.setText(genders[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        reg_btn.setOnClickListener {
            listOf(fio_edit_text, birth_date_edit_text, email_edit_text).map {
                if (it.text.isNullOrBlank()) {
                    context?.shortToast(getString(R.string.input_all_fields))
                    return@setOnClickListener
                }
            }
            PreferenceManager(requireContext()).apply {
                saveUser(
                    USER,
                    User(
                        fio_edit_text.text.toString(),
                        birth_date_edit_text.text.toString(),
                        gender_edit_text.text.toString(),
                        email_edit_text.text.toString()
                    )
                )
                saveBoolean(IS_LOGGED_IN, true)
            }
            findNavController(this).navigate(R.id.action_regFragment_to_profileFragment)
        }
    }
}