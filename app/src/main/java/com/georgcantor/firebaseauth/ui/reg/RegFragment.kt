package com.georgcantor.firebaseauth.ui.reg

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.georgcantor.firebaseauth.R
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
    }
}