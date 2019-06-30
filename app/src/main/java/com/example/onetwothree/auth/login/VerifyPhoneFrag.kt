package com.example.onetwothree.auth.login

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.onetwothree.R
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_verifiy_phone.*
import java.util.concurrent.TimeUnit

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [VerifyPhoneFrag.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [VerifyPhoneFrag.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VerifyPhoneFrag : Fragment() {

    private var rootView: View? = null
    private var mMobileNo: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var mCountryCode:String="+91"
    private var mVerificationCode:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            mMobileNo = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_verifiy_phone, container, false)
        return rootView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendVerificationCode()
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
        listener = null
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param mobileNo Parameter 1.
         * @return A new instance of fragment VerifyPhoneFrag.
         */
        @JvmStatic
        fun newInstance(mobileNo: String) =
            VerifyPhoneFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, mobileNo)
                }
            }
    }

    private fun sendVerificationCode() {
        PhoneAuthProvider.getInstance()
            .verifyPhoneNumber(mCountryCode + mMobileNo, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, mCallbacks)
    }


    var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationFailed(p0: FirebaseException?) {
                Log.v("*1-onFailed-",p0?.message)

            }

            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
                Log.v("*1-onCompleted-", p0?.smsCode)
                otp_et.setText(p0?.smsCode);

            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                Log.v("*1-onCodeSent-",p0+"--"+p1)
                mVerificationCode=p0
            }
        }
}
