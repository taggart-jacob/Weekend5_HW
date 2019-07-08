package com.example.weekend5_hw

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [EmployeesListFragment.OnListFragmentInteractionListener] interface.
 */
class EmployeesListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var department: String? = null

    private var listener: OnListFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        department = arguments?.getString(ARG_DEPARTMENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("TAG", department.toString())
        val dbHelp = EmployeeDatabaseHelper(context!!)
        var listEmployees = ArrayList<EmployeeModel>()
        class ThreadAll : Thread(){
            override fun run() {
                try {
                    if (department.equals("All Departments")){
                        listEmployees = dbHelp.getAllEmployees()
                    } else if (department.equals("Software")){
                        listEmployees = dbHelp.getEmployeesByDepartment("Software")
                    } else if (department.equals("Hardware")){
                        listEmployees = dbHelp.getEmployeesByDepartment("Hardware")
                    } else if (department.equals("Payroll")){
                        listEmployees = dbHelp.getEmployeesByDepartment("Payroll")
                    } else if (department.equals("Human Resources")){
                        listEmployees = dbHelp.getEmployeesByDepartment("Human Resources")
                    } else if (department.equals("Legal")){
                        listEmployees = dbHelp.getEmployeesByDepartment("Legal")
                    } else if (department.equals("Tyrants")){
                        listEmployees = dbHelp.getEmployeesByDepartment("Tyrants")
                    } else{
                        listEmployees.add(EmployeeModel())
                    }
                } catch (e: InterruptedException){
                    e.printStackTrace()
                }
                Log.d("TAG", listEmployees.toString())
            }
        }
        val threadDept = ThreadAll()
        threadDept.start()



        val view = inflater.inflate(R.layout.fragment_emp_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = EmployeeListRVAdapter(listEmployees, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onFragmentInteraction(item: EmployeeModel)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_DEPARTMENT = "department"
        const val ARG_COLUMN_COUNT = "column_count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(name: String) : EmployeesListFragment =
            EmployeesListFragment().apply {
                val args = Bundle()
                args.putString(ARG_DEPARTMENT, name)
                val fragment = this
                fragment.arguments = args
                return fragment
            }
    }
}
