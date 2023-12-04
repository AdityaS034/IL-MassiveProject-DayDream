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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ObatMasukFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ObatMasukFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ObatMasukFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}