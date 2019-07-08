package com.example.weekend5_hw

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.weekend5_hw.EmployeesListFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_emp_list_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class EmployeeListRVAdapter(
    private val mValues: ArrayList<EmployeeModel>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<EmployeeListRVAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as EmployeeModel
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onFragmentInteraction(item)

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_emp_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mFirstName.text = item.empFirstName
        holder.mLastName.text = item.empLastName
        holder.mAddress.text = item.empAddress
        holder.mCity.text = item.empCity
        holder.mState.text = item.empState
        holder.mZip.text = item.empZip
        holder.mTaxId.text = item.empTaxId
        holder.mPosition.text = item.empPosition
        holder.mDepartment.text = item.empDepartment

        with(holder.mView) {
            tag = item

            setOnClickListener(mOnClickListener)
        }

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mFirstName: TextView = mView.firstName
        val mLastName: TextView = mView.lastName
        val mAddress: TextView = mView.address
        val mCity: TextView = mView.city
        val mState: TextView = mView.state
        val mZip: TextView = mView.zip
        val mTaxId: TextView = mView.taxId
        val mPosition: TextView = mView.position
        val mDepartment: TextView = mView.department

    }
}
