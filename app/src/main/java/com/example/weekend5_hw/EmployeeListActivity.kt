
package com.example.weekend5_hw

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager



class EmployeeListActivity : AppCompatActivity(), EmployeesListFragment.OnListFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        val fragmentManager: FragmentManager = supportFragmentManager
        val dept = intent.getStringExtra("department")
        val employeesListFragment: EmployeesListFragment = EmployeesListFragment.newInstance(dept)
        Log.d("TAG", dept.toString())
        fragmentManager.beginTransaction().add(R.id.frmPlaceholder, employeesListFragment).addToBackStack("LIST").commit()

    }

    override fun onFragmentInteraction(item: EmployeeModel) {
        val intent = Intent(this, EmployeeDetailsActivity::class.java)
        Log.d("TAG", item.empState)
        intent.putExtra("employee", item)
        startActivity(intent)
    }
}
