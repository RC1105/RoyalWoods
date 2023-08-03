package com.nppr.royal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SummaryFragment : Fragment(R.layout.fragment_summary) {
    private  lateinit var one:String
    private  lateinit var two:String
    private  lateinit var three:String
    private  lateinit var four:String
    private  lateinit var five:String
    private  lateinit var six:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            one = it.getString("total").toString()
            two = it.getString("quant").toString()
            three = it.getString("token").toString()
            four = it.getString("mode").toString()
            five = it.getString("addr").toString()
            six = it.getString("time").toString()

            /*

                        bundle.putString("total", one.toString())
                        bundle.putString("quant", two.toString())
                        bundle.putString("token", rnd.toString())
                        bundle.putString("mode", model.toString())
                        bundle.putString("addr", address.toString())
                        bundle.putString("time", currentDate.toString())

             */
        }
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        //val tv:TextView=itemView.findViewById(R.id.tv_hbf)
        //tv.text="${one} ${two}"
        val tv1:TextView=itemView.findViewById(R.id.tv_mod)
        val tv2:TextView=itemView.findViewById(R.id.tv_token)
        val tv3:TextView=itemView.findViewById(R.id.tv_item)
        val tv4:TextView=itemView.findViewById(R.id.tv_pay)
        tv1.text=four.toString()
        tv2.text=three
        tv3.text=two
        tv4.text=one
        val backk:TextView=itemView.findViewById(R.id.tv_back)
        backk.setOnClickListener {
            val destinationFragment = HomeFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }
}