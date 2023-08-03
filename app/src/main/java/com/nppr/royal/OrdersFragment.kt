package com.nppr.royal

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class OrdersFragment : Fragment(R.layout.orders) {
    lateinit var recc: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recc = itemView.findViewById(R.id.rv_orders)
        recc.layoutManager = layoutManager
        var arrx= mutableListOf<orrData2>()
        var hash= emptyMap<String,String>()
        var addr=""
        var token=""
        var mob=""
        var mod=""
        var time=""
        var price=""
        var quant=""
        //var order=hash
        var key=""
        var order=""
        var food=""
        FirebaseFirestore.getInstance().collection("Orders").whereEqualTo("Phone","${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}").get().addOnSuccessListener {

            var hashic= mutableMapOf<String,String>()
            for(doc in it){
                addr=doc.data["Address"].toString()
                token=doc.data["ID"].toString()
                mob=doc.data["Mobile"].toString()
                mod=doc.data["Mode"].toString()
                time=doc.data["Time"].toString()
                price=doc.data["Price"].toString()
                quant=doc.data["Quant"].toString()
                key=doc.id.toString()
                arrx.add(orrData2(mod,price,quant,token,time,addr))


            }
        }.addOnCompleteListener {

            Handler().postDelayed({

                arrx.sortBy { it.dt }
                val adapter = orrAdapter(arrx)
                arrx.reverse()
                recc.adapter = adapter

            },2000)

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.orders, container, false)
    }
}