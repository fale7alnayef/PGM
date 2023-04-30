package com.example.pgm

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IVTFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IVTFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_i_v_t, container, false)

        val trainee = ArrayList<TraineeData>()
        val queue = Volley.newRequestQueue(activity?.applicationContext)
        val token = "Bearer " + Data.Token
        val url = "http://${Data.url}:8000/api/coach/show_private_users"
        val jsonObject = @RequiresApi(Build.VERSION_CODES.O)
        object : JsonObjectRequest(Method.POST, url, null, {
            try {
                val usersarray = it.getJSONArray("users")
                for (i in 0 until usersarray.length()) {
                    val firstName = usersarray.getJSONObject(i).getString("first_name")
                    val lastName = usersarray.getJSONObject(i).getString("last_name")
                    val birthday = usersarray.getJSONObject(i).getString("birthday")
                    val height = usersarray.getJSONObject(i).getString("height")
                    val weight = usersarray.getJSONObject(i).getString("weight")
                    val id = usersarray.getJSONObject(i).getString("id")
                    val phoneNumber = usersarray.getJSONObject(i).getString("phone_number")
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val date = LocalDate.parse(birthday, formatter)
                    val age = (LocalDate.now().compareTo(date)).toString()
                    val imgURL = usersarray.getJSONObject(i).getString("img_url")

                    trainee.add(
                        TraineeData(
                            "$firstName $lastName",
                            "blah",
                            imgURL,
                            age,
                            height,
                            weight,
                            phoneNumber,
                            id
                        )
                    )

                    val rv = view.findViewById<RecyclerView>(R.id.privateRecycler)?.let { rv ->
                        rv.layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                        rv.adapter = NormalPrivateTraineeAdapter(requireContext(), trainee)
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
         * @return A new instance of fragment IVTFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IVTFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}