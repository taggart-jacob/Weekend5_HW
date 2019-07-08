package com.example.weekend5_hw

import android.provider.BaseColumns

object EmployeeDatabaseContract {
    class EmployeeTable : BaseColumns{
        companion object{
            val TABLE_NAME = "employee_table"
            val COL_EMPLOYEE_FIRST_NAME = "first_name"
            val COL_EMPLOYEE_LAST_NAME = "last_name"
            val COL_EMPLOYEE_ADDRESS = "address"
            val COL_EMPLOYEE_CITY = "city"
            val COL_EMPLOYEE_STATE = "state"
            val COL_EMPLOYEE_ZIP = "zip"
            val COL_EMPLOYEE_TAX_ID = "tax_id"
            val COL_EMPLOYEE_POSITION = "position"
            val COL_EMPLOYEE_DEPARTMENT = "department"
        }
    }

    class DepartmentTable : BaseColumns{
        companion object{
            val TABLE_NAME = "department_table"
            val COL_DEPARTMENT = "department"
        }
    }
}