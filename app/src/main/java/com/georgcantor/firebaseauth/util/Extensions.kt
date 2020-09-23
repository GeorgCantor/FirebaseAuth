package com.georgcantor.firebaseauth.util

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.georgcantor.firebaseauth.util.Constants.PHONE_MASK
import com.google.android.material.textfield.TextInputLayout
import com.redmadrobot.inputmask.MaskedTextChangedListener

fun Context.shortToast(message: String?) = Toast.makeText(this, message, LENGTH_SHORT).show()

fun Activity.showKeyboard() {
    (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(currentFocus, SHOW_IMPLICIT)
}

fun EditText.setMaskListener(input: TextInputLayout?) {
    class PhoneMaskListener : MaskedTextChangedListener(PHONE_MASK, this@setMaskListener, object : ValueListener {
        override fun onTextChanged(maskFilled: Boolean, extractedValue: String, formattedValue: String) {
            input?.error = null
        }
    })
    addTextChangedListener(PhoneMaskListener())
}