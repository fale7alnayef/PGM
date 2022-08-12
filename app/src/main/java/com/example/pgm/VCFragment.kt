package com.example.pgm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VCFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VCFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_v_c, container, false)

        val contractt = ArrayList<SCData>()

        val queue = Volley.newRequestQueue(activity?.applicationContext)
        val token = "Bearer " + Data.Token
        val url = "http://${Data.url}:8000/api/admin/coach_available"

        val jsonObject = object : JsonObjectRequest(Method.POST, url, null, {
            try {
                val usersarray = it.getJSONArray("Available_coaches")
                for (i in 0 until usersarray.length()) {
                    val contracts =
                        usersarray.getJSONObject(i).getJSONObject("info").getJSONObject("contract")
                    val firstName =
                        usersarray.getJSONObject(i).getJSONObject("info").getString("first_name")
                    val lastName =
                        usersarray.getJSONObject(i).getJSONObject("info").getString("last_name")
                    contractt.add(
                        SCData(
                            "$firstName $lastName",
                            contracts.getString("salary"),
                            contracts.getString("start_date").substring(0, 10),
                            contracts.getString("end_date").substring(0, 10),
                            contracts.getString("coach_id")

                            )
                    )



                    val rv = view.findViewById<RecyclerView>(R.id.activeContsRecycler)?.let { rv ->
                        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                        rv.adapter = ActiveContractAdapter(requireContext(), contractt)
                    }

                }
            } catch (e: Exception) {
                Toast.makeText(activity?.applicationContext, e.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }, {
            Toast.makeText(activity?.applicationContext, it.toString(), Toast.LENGTH_LONG).show()
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = token
                return headers
            }
        }

        queue.add(jsonObject)


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VCFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VCFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}