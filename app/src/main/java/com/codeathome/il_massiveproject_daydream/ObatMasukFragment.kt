package com.codeathome.il_massiveproject_daydream

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ccom.codeathome.il_massiveproject_daydream.model.UserData
import com.codeathome.il_massiveproject_daydream.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ObatMasukFragment : Fragment() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_obat_masuk, container, false)

        addsBtn = view.findViewById(R.id.addingBtn)
        recv = view.findViewById(R.id.mRecycler)

        userList = ArrayList()
        userAdapter = UserAdapter(requireActivity(), userList)

        recv.layoutManager = LinearLayoutManager(requireActivity())
        recv.adapter = userAdapter

        addsBtn.setOnClickListener { addInfo() }

        return view
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(requireActivity())
        val v = inflater.inflate(R.layout.activity_tambah_obat_masuk, null)

        val userName = v.findViewById<EditText>(R.id.edtNamaObat)
        val userNo = v.findViewById<EditText>(R.id.edtJumlahObat)

        val addDialog = AlertDialog.Builder(requireActivity())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("Name: $names", "Jumlah. : $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(requireActivity(), "Adding User Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireActivity(), "Cancel", Toast.LENGTH_SHORT).show()
        }

        addDialog.create()
        addDialog.show()
    }
}