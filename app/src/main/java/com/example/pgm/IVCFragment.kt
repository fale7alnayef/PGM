package com.example.pgm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IVCFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IVCFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_i_v_c, container, false)

        val contract = listOf(
            SCData("ghassan","1000","2002/1/1","2002/1/3"),
            SCData("ameer","2000","2002/1/1","2002/1/3"),
            SCData("ahmad","9893", "2002/1/1","2002/1/3"),
            SCData("saif","42452", "2002/1/1","2002/1/3"),
            SCData("ghassan","453", "2002/1/1","2002/1/3"),
            SCData("ameer","3354", "2002/1/1","2002/1/3"),
            SCData("ghassan","1000","2002/1/1","2002/1/3"),
            SCData("ameer","2000","2002/1/1","2002/1/3"),
            SCData("ahmad","9893", "2002/1/1","2002/1/3"),
            SCData("saif","42452", "2002/1/1","2002/1/3"),
            SCData("ghassan","453", "2002/1/1","2002/1/3"),
            SCData("ameer","3354", "2002/1/1","2002/1/3")
        )



        val rv   = view.findViewById<RecyclerView>(R.id.inactiveContsRecycler)?.let { rv ->
            rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rv.adapter = ContractAdapter(requireContext(),contract)
        }



        return view    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IVCFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IVCFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}