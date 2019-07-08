package com.example.weekend5_hw

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.Connection
import java.util.*

class EmployeeDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.beginTransaction()
        try {
            db.execSQL(SQL_CREATE_DEPARTMENT_ENTRIES)
            db.execSQL(SQL_CREATE_EMPLOYEE_ENTRIES)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAllEmployees(): ArrayList<EmployeeModel> {
        val employeeList = ArrayList<EmployeeModel>()
        val sqLiteDatabase = this.readableDatabase
        val cursor: Cursor

        cursor = sqLiteDatabase.rawQuery(SELECT_ALL_EMPLOYEES_QUERY, null)
        if (cursor.moveToFirst()) {
            do {
                val employeeModel = EmployeeModel()
                employeeModel.empFirstName = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_FIRST_NAME))
                employeeModel.empLastName = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_LAST_NAME))
                employeeModel.empAddress = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ADDRESS))
                employeeModel.empCity = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_CITY))
                employeeModel.empState = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_STATE))
                employeeModel.empZip = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ZIP))
                employeeModel.empTaxId = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_TAX_ID))
                employeeModel.empPosition = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_POSITION))
                employeeModel.empDepartment = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_DEPARTMENT))

                employeeList.add(employeeModel)
            } while (cursor.moveToNext())
        }
        cursor.close()
        sqLiteDatabase.close()
        return employeeList
    }

    fun getEmployeesByDepartment(dept: String): ArrayList<EmployeeModel> {
        val employeeList = ArrayList<EmployeeModel>()
        val sqLiteDatabase = this.readableDatabase
        val cursor: Cursor

        cursor = sqLiteDatabase.rawQuery(getSelectByDepartmentQuery(dept), null)

        if (cursor.moveToFirst()) {
            do {
                val employeeModel = EmployeeModel()
                employeeModel.empFirstName = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_FIRST_NAME))
                employeeModel.empLastName = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_LAST_NAME))
                employeeModel.empAddress = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ADDRESS))
                employeeModel.empCity = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_CITY))
                employeeModel.empState = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_STATE))
                employeeModel.empZip = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ZIP))
                employeeModel.empTaxId = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_TAX_ID))
                employeeModel.empPosition = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_POSITION))
                employeeModel.empDepartment = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_DEPARTMENT))

                employeeList.add(employeeModel)
            } while (cursor.moveToNext())
        }
        cursor.close()
        sqLiteDatabase.close()
        return employeeList
    }

    fun getEmployeeByTaxId(taxId: String): EmployeeModel {
        val employeeModel = EmployeeModel()
        val sqLiteDatabase = this.readableDatabase
        val cursor: Cursor

        cursor = sqLiteDatabase.rawQuery(getSelectByTaxIdQuery(taxId), null)

        if (cursor.moveToFirst()) {
            employeeModel.empFirstName = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_FIRST_NAME))
            employeeModel.empLastName = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_LAST_NAME))
            employeeModel.empAddress = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ADDRESS))
            employeeModel.empCity = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_CITY))
            employeeModel.empState = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_STATE))
            employeeModel.empZip = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ZIP))
            employeeModel.empTaxId = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_TAX_ID))
            employeeModel.empPosition = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_POSITION))
            employeeModel.empDepartment = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_DEPARTMENT))
        }
        cursor.close()
        sqLiteDatabase.close()
        return employeeModel
    }

    fun insertEmployeeIntoDatabase(employee: EmployeeModel) {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_FIRST_NAME, employee.empFirstName)
        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_LAST_NAME, employee.empLastName)
        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ADDRESS, employee.empAddress)
        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_CITY, employee.empCity)
        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_STATE, employee.empState)
        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ZIP, employee.empZip)
        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_TAX_ID, employee.empTaxId)
        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_POSITION, employee.empPosition)
        contentValues.put(EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_DEPARTMENT, employee.empDepartment)

        sqLiteDatabase.insert(EmployeeDatabaseContract.EmployeeTable.TABLE_NAME, null, contentValues)
        sqLiteDatabase.close()
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Company.db"

        private val SQL_CREATE_EMPLOYEE_ENTRIES =
            "CREATE TABLE " + EmployeeDatabaseContract.EmployeeTable.TABLE_NAME + " (" +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_FIRST_NAME + " TEXT," +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_LAST_NAME + " TEXT," +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ADDRESS + " TEXT," +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_CITY + " TEXT," +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_STATE + " TEXT," +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_ZIP + " TEXT," +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_TAX_ID + " TEXT PRIMARY KEY," +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_POSITION + " TEXT," +
                    EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_DEPARTMENT + " TEXT)"

        private val SQL_DELETE_EMPLOYEE_ENTRIES =
            "DROP TABLE IF EXISTS " + EmployeeDatabaseContract.EmployeeTable.TABLE_NAME

        private val SQL_CREATE_DEPARTMENT_ENTRIES =
            "CREATE TABLE " + EmployeeDatabaseContract.DepartmentTable.TABLE_NAME + " (" +
                    EmployeeDatabaseContract.DepartmentTable.COL_DEPARTMENT + " TEXT PRIMARY KEY)"

        //queries
        private val BASE_SELECT_QUERY = "SELECT * FROM %s"
        val SELECT_ALL_EMPLOYEES_QUERY =
            String.format(Locale.US, BASE_SELECT_QUERY, EmployeeDatabaseContract.EmployeeTable.TABLE_NAME)

        val SELECT_BY_TAXID_QUERY = String.format(Locale.US, "%s WHERE %s = ", BASE_SELECT_QUERY,
            EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_TAX_ID)
        fun getSelectByTaxIdQuery(taxId: String): String {
            return String.format(Locale.US, "%s \'%s\'", SELECT_BY_TAXID_QUERY, taxId)
        }


        /*val SELECT_ALL_BY_DEPARTMENT = String.format(Locale.US, "%s WHERE %s", BASE_SELECT_QUERY, EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_DEPARTMENT)
        fun getSelectByDepartmentQuery(dept: String): String {
            return String.format(Locale.US, "%s \'%s\'", SELECT_ALL_BY_DEPARTMENT, dept)
        }*/

        fun getSelectByDepartmentQuery(title: String): String {
            return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"%s\"", EmployeeDatabaseContract.EmployeeTable.TABLE_NAME,
                EmployeeDatabaseContract.EmployeeTable.COL_EMPLOYEE_DEPARTMENT, title)
        }



    }
}