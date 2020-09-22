package com.georgcantor.firebaseauth.util

import android.widget.EditText
import com.georgcantor.firebaseauth.util.Constants.PHONE_MASK
import com.google.android.material.textfield.TextInputLayout
import com.redmadrobot.inputmask.MaskedTextChangedListener

fun EditText.setMaskListener(input: TextInputLayout?) {
    class PhoneMaskListener : MaskedTextChangedListener(PHONE_MASK, this@setMaskListener, object : ValueListener {
        override fun onTextChanged(maskFilled: Boolean, extractedValue: String, formattedValue: String) {
            input?.error = null
        }
    })
    addTextChangedListener(PhoneMaskListener())
}