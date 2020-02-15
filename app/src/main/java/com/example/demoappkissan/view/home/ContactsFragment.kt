package com.example.demoappkissan.view.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.demoappkissan.R
import com.example.demoappkissan.adapter.ContactListAdapter
import com.example.demoappkissan.extensions.showMessage
import com.example.demoappkissan.model.ContactDetails
import com.example.demoappkissan.model.MyContactsModel
import com.example.demoappkissan.utils.OnItemClick
import com.example.demoappkissan.utils.Utils
import com.example.demoappkissan.view.ReusableActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(), OnItemClick {

    val adapter by lazy { ContactListAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setAdapter()
        getContactsFromJson()
    }

    private fun setAdapter() {
        rvContactsDetails.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvContactsDetails.adapter = adapter
    }

    private fun getContactsFromJson() {

        val jsonData: String? = Utils.getJsonFromAssets(activity!!, "data.json")
        if (jsonData != null) {
            val parsedData: MyContactsModel = Gson().fromJson(jsonData, MyContactsModel::class.java)
            Log.d("anshul", Gson().toJson(parsedData))
            parsedData.contactDetails?.let {
                adapter.addData(it)
            }
        } else {
            showMessage(activity!!, "Failed to load Json data.")
        }

    }

    override fun onItemClick(any: Any) {
        when (any) {
            is ContactDetails -> {
                val intent = Intent(activity!!,ReusableActivity::class.java)
                intent.putExtra("data",any)
                startActivity(intent)
            }
        }
    }
}
