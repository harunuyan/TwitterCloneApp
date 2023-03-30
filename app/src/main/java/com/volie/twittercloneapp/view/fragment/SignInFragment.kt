package com.volie.twittercloneapp.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.twittercloneapp.databinding.FragmentSignInBinding
import java.text.SimpleDateFormat
import java.util.*

class SignInFragment : Fragment() {
    private var _mBinding: FragmentSignInBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSignInBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCalendar()

        mBinding.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        mBinding.etPhone.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                mBinding.outlinedTextFieldPhone.hint = "Phone"
                mBinding.btnUseEmailInstead.visibility = View.VISIBLE
            } else {
                mBinding.btnUsePhoneInstead.visibility = View.GONE
            }
        }

        mBinding.btnUsePhoneInstead.setOnClickListener {
            it.visibility = View.GONE
            mBinding.btnUseEmailInstead.visibility = View.VISIBLE
            mBinding.outlinedTextFieldPhone.hint = "Phone"
            mBinding.etPhone.inputType = android.text.InputType.TYPE_CLASS_PHONE
        }

        mBinding.btnUseEmailInstead.setOnClickListener {
            it.visibility = View.GONE
            mBinding.btnUsePhoneInstead.visibility = View.VISIBLE
            mBinding.outlinedTextFieldPhone.hint = "Email"
            mBinding.etPhone.inputType = android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
    }

    private fun setupCalendar() {
        val calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                mBinding.etDateOfBirth.setText(dateFormatter.format(calendar.time))
            }

        mBinding.etDateOfBirth.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog =
                DatePickerDialog(requireContext(), dateSetListener, year, month, day)
            datePickerDialog.show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}