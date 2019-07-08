package com.example.weekend5_hw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.activity_filter_employee.*

class FilterEmployeeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var departments = arrayOf("NO SELECTION", "All Departments", "Software", "Hardware", "Payroll", "Human Resources", "Legal", "Tyrants")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_employee)

        val spinner = this.spinDepartments
        spinner.setOnItemSelectedListener(this)

        val anAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, departments)
        anAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        with(spinner) {
            adapter = anAdapter
            setSelection(0, false)
            gravity = Gravity.CENTER
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(view?.context, EmployeeListActivity::class.java)
        if (position == 0){

        } else {
            intent.putExtra("department", departments[position])
            startActivity(intent)
        }
    }
}
