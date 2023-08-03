package com.nppr.royal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class cartFragment : Fragment(R.layout.fragment_cart) {

    lateinit var recc: RecyclerView
    lateinit var  amtt:TextView

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val txx:TextView=itemView.findViewById(R.id.tv_ycie)
        var arrx = mutableListOf<homeModel>()
        amtt= itemView.findViewById(R.id.tv_Amt)
        recc = itemView.findViewById(R.id.rv_carrt)
        val exxer:LinearLayout = itemView.findViewById(R.id.ll_extraa)
        val amtt:TextView = itemView.findViewById(R.id.tv_Amt)
        val pro:TextView = itemView.findViewById(R.id.tv_proceed)
/*
        arrx.add(homeModel(R.drawable.food,"Paneer Tikka",8,100,200))
        arrx.add(homeModel(R.drawable.food,"Paneer Tikka",8,100,200))
        arrx.add(homeModel(R.drawable.food,"Paneer Tikka",8,100,200))
        arrx.add(homeModel(R.drawable.food,"Paneer Tikka",8,100,200))
        arrx.add(homeModel(R.drawable.food,"Paneer Tikka",8,100,200))
        arrx.add(homeModel(R.drawable.food,"Paneer Tikka",8,100,200))
        arrx.add(homeModel(R.drawable.food,"Paneer Tikka",8,100,200))
*/
        var hashher= mutableMapOf(
            "Name" to "dfd"
        )
        var img=""
        var name=""
        var cost=""
        var type=""
        var quant=0
        var id=""
        var ans=0
        pro.setOnClickListener {
            if(amtt.text=="0"){
                recc.visibility=View.GONE
                txx.visibility=View.VISIBLE
                exxer.visibility=View.GONE
            }
            else{
                Log.i("jsr","${arrx.toString()}")

                var uka= mutableMapOf<String,String>()
                for(i in arrx){
                    val q=i.qty
                    val id=i.id
                    uka.put(id.toString(),q.toString())

                }
                var doccie:String=""
                var name:String=""
                var phone:String=""
                FirebaseFirestore.getInstance().collection("Users").whereEqualTo("Phone",FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()).get().addOnSuccessListener {
                    Log.e("hahah","yoyo ${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}")
                    //.toObjects()

                    for(doc in it){
                        doccie=doc.id.toString()
                        name=doc.data["Name"].toString()
                        phone=doc.data["Phone"].toString()

                    }
                }.addOnCompleteListener {

                    Log.i("hahah", "this is the hashh ${uka.toString()}")
                    var userMap = mutableMapOf(
                        "Name" to name,
                        "Cart" to uka,
                        "Phone" to phone
                    )
                    FirebaseFirestore.getInstance().collection("Users").document(doccie)
                        .set(userMap).addOnSuccessListener {
                        Log.i("hahah", "safaltaa")
                    }
                    val destinationFragment = orderFragment()
                    val bundle = Bundle()
                    val amt = "${amtt.text}"
                    var amoun=0
                    for(i in arrx){
                        amoun+=i.qty.toString().toInt()*i.price.toString().toInt()
                    }
                    bundle.putString("total", amoun.toString())
                    bundle.putString("quant", arrx.size.toString())
                    destinationFragment.arguments = bundle

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }

        }
        FirebaseFirestore.getInstance().collection("Users").whereEqualTo("Phone",FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()).get().addOnSuccessListener {

            for (doc in it){

                hashher=doc.data["Cart"] as MutableMap<String, String>
                if(doc.data["Cart"]==null || doc.data["Cart"].toString()=="{}"  ){
                    Log.e("hahah","j baat")
                    recc.visibility=View.GONE
                    txx.visibility=View.VISIBLE
                    exxer.visibility=View.GONE

                    Log.e("hahah","${hashher.size}")
                }
                else{
                    txx.visibility=View.GONE
                    recc.visibility=View.VISIBLE

                    exxer.visibility=View.VISIBLE
                }
                for(ele in hashher){
                    Log.e("hahah","${ele.key} matt ${ele.value[0]}")
                    FirebaseFirestore.getInstance().collection("Food").document(ele.key).get().addOnSuccessListener {
                        img=it.get("Image").toString()
                        name=it.get("Name").toString()
                        cost=it.get("Price").toString()
                        type=it.get("Type").toString()
                        quant=ele.value[0].toString().toInt()
                        ans=ans+cost.toInt()*quant
                        id=ele.key.toString()
                    }.addOnCompleteListener {

                        arrx.add(homeModel(id,img,name,cost,type,quant))
                        Log.i("hahah",arrx.toString())
                        val layoutManager = LinearLayoutManager(context)
                        recc.layoutManager = layoutManager
                        val adapter = cartAdapter(arrx, this@cartFragment)
                        recc.adapter = adapter
                        amtt.text=ans.toString()+"/-"


                    }
                }
            }
        }.addOnCompleteListener {

        }

    }
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view=inflater.inflate(R.layout.fragment_cart, container, false)
        return view
    }

     fun onChangeQty(ans: Int): Int {
        amtt.text = ans.toString()
        return 0
    }
}