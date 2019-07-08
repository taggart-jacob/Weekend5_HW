package com.example.weekend5_hw

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager

class EmployeeDetailsActivity : AppCompatActivity(), EmployeeDetailsFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        val fragmentManager: FragmentManager = supportFragmentManager
        val employeeModel: EmployeeModel = intent.getParcelableExtra("employee")
        Log.d("TAG", employeeModel.empState)
        val employeeDetailsFragment: EmployeeDetailsFragment = EmployeeDetailsFragment.newInstance(employeeModel)
        fragmentManager.beginTransaction().add(R.id.frmPlaceholderDetails, employeeDetailsFragment)
            .addToBackStack("DETAILS").commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
