package com.example.weekend5_hw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlin.concurrent.thread
import androidx.core.os.HandlerCompat.postDelayed


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()
        val dbHelper = EmployeeDatabaseHelper(this)

        class Thread1 : Thread() {
            override fun run(): Unit {
                try {
                    if (dbHelper.getAllEmployees() == null) {
                        dbHelper.insertEmployeeIntoDatabase(
                            EmployeeModel
                                (
                                "John", "Zorn", "22 One St"
                                , "Atlanta", "GA", "30080", "34", "Chief", "Tyrants"
                            )
                        )
                        dbHelper.insertEmployeeIntoDatabase(
                            EmployeeModel
                                (
                                "Joseph", "Heller", "899 S Vineyard Ave", "Stockton",
                                "California", "23345", "35", "Programmer", "Software"
                            )
                        )
                        dbHelper.insertEmployeeIntoDatabase(
                            EmployeeModel
                                (
                                "Mary", "Kate", "23 Cool Pl", "Jordan",
                                "Minnesota", "89773", "39", "Lawyer", "Legal"
                            )
                        )
                        dbHelper.insertEmployeeIntoDatabase(
                            EmployeeModel
                                (
                                "Richard", "Smoker", "1 First Cir", "Chicago",
                                "Illinois", "50021", "98", "Manager", "Payroll"
                            )
                        )
                        dbHelper.insertEmployeeIntoDatabase(
                            EmployeeModel
                                (
                                "D", "Boon", "22 Hardcore St", "San Pedro",
                                "California", "23331", "82", "Analyst", "Hardware"
                            )
                        )
                        dbHelper.insertEmployeeIntoDatabase(
                            EmployeeModel
                                (
                                "Steve", "Albini", "33 Numero Ave", "Evanston",
                                "Illinois", "23345", "71", "GOAT", "Human Resources"
                            )
                        )
                        dbHelper.insertEmployeeIntoDatabase(
                            EmployeeModel
                                (
                                "Geddy", "Lee", "12 Fort St", "Billings",
                                "Montana", "77990", "74", "Engineer", "Hardware"
                            )
                        )
                        dbHelper.insertEmployeeIntoDatabase(
                            EmployeeModel
                                (
                                "David", "Yow", "90 Tea St", "Denver", "Colorado",
                                "72166", "89099", "Programmer", "Software"
                            )
                        )
                    }

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                Log.d("TAG", dbHelper.getAllEmployees().toString())
            }
        }

        val thread1 = Thread1()
        thread1.start()
        startActivity(Intent(this, FilterEmployeeActivity::class.java))
    }
}
