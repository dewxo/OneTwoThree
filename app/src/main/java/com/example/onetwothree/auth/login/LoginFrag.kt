package com.example.onetwothree.auth.login

import android.arch.core.executor.TaskExecutor
import android.content.Context
import android.content.res.Configuration
import android.inputmethodservice.Keyboard
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.util.TimeUtils
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.onetwothree.R
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFrag.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFrag.newInstance] factory method to
 * create an instance of this fragment.
 *
 */


class LoginFrag : Fragment(), TextWatcher {

    private var rootView: View? = null
    private var mobileNoEt: TextInputEditText? = null;
    private var mobileNoITLayout: TextInputLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        mobileNoEt = rootView!!.findViewById(R.id.mobile_et)
        mobileNoITLayout = rootView!!.findViewById(R.id.mobile_no_t_i_layout)
        mobileNoEt?.setRawInputType(Configuration.KEYBOARD_12KEY);

        /*
        * click listener for buttons
        */
        rootView!!.mobile_signIn_bt.setOnClickListener(View.OnClickListener {

            if (isMobileNoValid())
                activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                    ?.replace(
                        R.id.container,
                        VerifyPhoneFrag.newInstance(mobileNo = mobileNoEt?.text.toString())
                    )?.commit()
        })

        /*
        *attach Text watcher listener for text change
        */

        mobileNoEt?.addTextChangedListener(this)
        return rootView;

    }




    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        isMobileNoValid()
    }


    private fun isMobileNoValid(): Boolean {
        if (mobileNoEt?.text.toString().trim().isEmpty()) {
            mobileNoEt?.requestFocus()
            mobileNoITLayout?.error = getString(R.string.enter_mobile_no_required)
            return false
        } else if (mobileNoEt?.text.toString().length < 10) {
            mobileNoEt?.requestFocus()
            mobileNoITLayout?.error = getString(R.string.error_mobile_no_length)
            return false
        } else {
            mobileNoITLayout?.isErrorEnabled = false
        }
        return true
    }
}

