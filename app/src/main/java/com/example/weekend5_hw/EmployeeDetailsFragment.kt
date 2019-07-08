package com.example.weekend5_hw

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_employee_details.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EmployeeDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EmployeeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EmployeeDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: EmployeeModel? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        param1 = arguments?.getParcelable(ARG_PARAM1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvFirstNameDetail.text = param1?.empFirstName
        tvLastNameDetail.text = param1?.empLastName
        tvAddressDetail.text = param1?.empAddress
        tvCityDetail.text = param1?.empCity
        tvStateDetail.text = param1?.empState
        tvZipDetail.text = param1?.empZip
        tvTaxIdDetail.text = param1?.empTaxId
        tvPositionDetail.text = param1?.empPosition
        tvDepartmentDetail.text = param1?.empDepartment
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: EmployeeModel) =
            EmployeeDetailsFragment().apply {
                    val args = Bundle()
                    args.putParcelable(ARG_PARAM1, param1)
                    val fragment = this
                    fragment.arguments = args
                    return fragment

            }
    }
}
