package com.nppr.royal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random.Default.nextInt
import kotlin.random.Random

class orderFragment : Fragment(R.layout.fragment_order) {
    private  lateinit var one:String
    private  lateinit var two:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            one = it.getString("total").toString()
            two = it.getString("quant").toString()

        }
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val tv: TextView =itemView.findViewById(R.id.tv_Items)
        val tv2: TextView =itemView.findViewById(R.id.tv_Amt)
        val tv3: TextView =itemView.findViewById(R.id.tv_total)
        val tv4: TextView =itemView.findViewById(R.id.tv_taxx)
        val tv5: TextView =itemView.findViewById(R.id.tv_coupon)
        val twentyfive: TextView =itemView.findViewById(R.id.percent25)
        val tv6: TextView =itemView.findViewById(R.id.tv_homeDel)
        val nexx: TextView =itemView.findViewById(R.id.tv_next)
        val dine: RadioButton =itemView.findViewById(R.id.rbDine)
        val home: RadioButton =itemView.findViewById(R.id.rbHome)
        val ll_25:LinearLayout=itemView.findViewById(R.id.ll_flat25)
        val ll_50:LinearLayout=itemView.findViewById(R.id.ll_flat50)
        val ll_free:LinearLayout=itemView.findViewById(R.id.ll_free)
        val summary:LinearLayout=itemView.findViewById(R.id.ll_os)
        val coupons:LinearLayout=itemView.findViewById(R.id.ll_coups)
        val dinein:LinearLayout=itemView.findViewById(R.id.ll_dine_in)
        val dineout:LinearLayout=itemView.findViewById(R.id.ll_deliver)
        val rg:LinearLayout=itemView.findViewById(R.id.rgUnits)
        val user:LinearLayout=itemView.findViewById(R.id.ll_user)
        val modd:EditText=itemView.findViewById(R.id.mode)
        val mobb:EditText=itemView.findViewById(R.id.input_mobile_number)

        val procAdd: TextView =itemView.findViewById(R.id.tv_proceed)
        val procDine: TextView =itemView.findViewById(R.id.tv_proceed2)
        val addr: EditText =itemView.findViewById(R.id.input_address)
        val naam: EditText =itemView.findViewById(R.id.input_name)
        var uka= mutableMapOf(
            "Name" to "dfd"
        )
        mobb.setText("${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)}")
        Log.e("haha","Zero")
        procDine.setOnClickListener {
            Log.e("haha","1.5")
            Log.e("haha","${addr.text.toString()}")
            if(addr.text.toString()!="null" && addr.text.toString()!="" && naam.text.toString()!="null" && naam.text.toString()!="" ){
                Log.e("haha","One")
                var name=naam.text.toString()
                var address=addr.text.toString()
                var mobile=mobb.text.toString()
                var phone=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()
                var model="HOME DELIVERY"
                FirebaseFirestore.getInstance().collection("Users").whereEqualTo("Phone",phone).get().addOnSuccessListener {
                    Log.e("haha","Two")
                    for(doc in it){
                        var hashher=doc.data["Cart"] as MutableMap<String, String>
                        uka=hashher
                    }
                }.addOnCompleteListener {
                    Log.e("haha", "Three")
                    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                    val currentDate = sdf.format(Date())
                    val rnd = Random.nextInt(1000000, 1999999) //rand(start, end)
                    val h = hashMapOf(
                        "Name" to name,
                        "Address" to address,
                        "Mobile" to mobile,
                        "Phone" to phone,
                        "Mode" to model,
                        "Order" to uka,
                        "Time" to currentDate,
                        "ID" to rnd,
                        "Quant" to two.toString(),
                        "Price" to one.toString()
                    )
                    FirebaseFirestore.getInstance().collection("Orders").document().set(h)
                        .addOnSuccessListener {
                            Log.e("haha", "Four")

                            val destinationFragment = SummaryFragment()
                            val bundle = Bundle()
                            bundle.putString("total", one.toString())
                            bundle.putString("quant", two.toString())
                            bundle.putString("token", rnd.toString())
                            bundle.putString("mode", model.toString())
                            bundle.putString("addr", address.toString())
                            bundle.putString("time", currentDate.toString())

                            destinationFragment.arguments = bundle

                            parentFragmentManager.beginTransaction()
                                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                                .addToBackStack(null)
                                .commit()

                        }
                }

            }
        }
        procAdd.setOnClickListener {
            if(naam.text.toString()!="null" && naam.text.toString()!=""){
                var name=naam.text.toString()
                var address=""
                var mobile=mobb.text.toString()
                var phone=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()
                var model="Dine In"
                Log.e("haha","1.5")

                Log.e("haha","${addr.text.toString()}")
                if( naam.text.toString()!="null" && naam.text.toString()!="" ){
                    Log.e("haha","One")
                    var name=naam.text.toString()
                    var address=""
                    var mobile=mobb.text.toString()
                    var phone=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()
                    var model="DINE IN"
                    FirebaseFirestore.getInstance().collection("Users").whereEqualTo("Phone",phone).get().addOnSuccessListener {
                        Log.e("haha","Two")
                        for(doc in it){
                            var hashher=doc.data["Cart"] as MutableMap<String, String>
                            uka=hashher
                        }
                    }.addOnCompleteListener {
                        Log.e("haha","Three")
                        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                        val currentDate = sdf.format(Date())
                        val rnd= Random.nextInt(100, 200 ) //rand(start, end)

                        val h= hashMapOf(
                            "Name" to name,
                            "Address" to "",
                            "Mobile" to mobile,
                            "Phone" to phone,
                            "Mode" to model,
                            "Order" to uka,
                            "Time" to currentDate,
                            "ID" to rnd,
                            "Quant" to two.toString(),
                            "Price" to one.toString()
                        )
                        FirebaseFirestore.getInstance().collection("Orders").document().set(h).addOnSuccessListener {
                            Log.e("haha","Four")
                            val destinationFragment = SummaryFragment()
                            val bundle = Bundle()
                            bundle.putString("total", one.toString())
                            bundle.putString("quant", two.toString())
                            bundle.putString("token", rnd.toString())
                            bundle.putString("mode", model.toString())
                            bundle.putString("time", currentDate.toString())

                            destinationFragment.arguments = bundle

                            parentFragmentManager.beginTransaction()
                                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                                .addToBackStack(null)
                                .commit()

                        }
                    }

                }
            }
        }
        var dis=0
        var del=0

        nexx.setOnClickListener {
            summary.visibility=View.GONE
            coupons.visibility=View.GONE
            rg.visibility=View.GONE
            user.visibility=View.VISIBLE

            if(dine.isChecked){
                dinein.visibility=View.VISIBLE
                dineout.visibility=View.GONE
                modd.setText("Dine In")
            }
            if(home.isChecked){
                dinein.visibility=View.GONE
                dineout.visibility=View.VISIBLE
                modd.setText("Home Delivery")
            }
        }
        twentyfive.text="SAVE ₹ ${(one.toString().toInt()*0.25).toString()}"

        if(dine.isChecked){
            del=0
            tv6.visibility=View.GONE
        }
        else{
            del=100
        }
        val tax=one.toString().toInt()*0.05

        val total=tax+one.toString().toInt()-dis
        if(dis==0){
            tv5.visibility=View.GONE
        }
        tv5.text="Discount:    ₹${dis}"
        tv4.text="Tax (5%):    ₹${tax.toString()}"
        tv2.text="Amount:      ₹${one}"
        tv3.text="\nTOTAL:      ₹${total.toString()}"
        tv.text="Total Items: ${two}"
        ll_25.setOnClickListener {
            if(one.toString().toInt() >2500){
                dis= (0.25*one.toString().toInt()).toInt()
                if(dis!=0){
                    tv5.visibility=View.VISIBLE
                }

                val total=tax+one.toString().toInt()-dis
                tv5.text="Discount:   -₹${dis}  (SUPER25)"
                tv4.text="Tax (5%):    ₹${tax.toString()}"
                tv2.text="Amount:      ₹${one}"
                tv3.text="\nTOTAL:      ₹${total.toString()}"
                tv.text="Total Items: ${two}"
            }
            else{

            }
        }
        ll_50.setOnClickListener {
            if(one.toString().toInt() >200){
                dis= 50//(0.25*one.toString().toInt()).toInt()
                if(dis!=0){
                    tv5.visibility=View.VISIBLE
                }

                val total=tax+one.toString().toInt()-dis
                tv5.text="Discount:   -₹${dis}   " + "(SAVE50)"
                tv4.text="Tax (5%):    ₹${tax.toString()}"
                tv2.text="Amount:      ₹${one}"
                tv3.text="\nTOTAL:      ₹${total.toString()}"
                tv.text="Total Items: ${two}"
            }
            else{

            }
        }
        ll_free.setOnClickListener {
            if(home.isChecked){
                    //if(one.toString().toInt() >2500){
                        dis= 100
                        if(dis!=0){
                            tv5.visibility=View.VISIBLE
                        }

                    val total=tax+one.toString().toInt()-dis
                        tv5.text="Discount:   -₹${dis}   (FREEDELIVERY)"
                        tv4.text="Tax (5%):    ₹${tax.toString()}"
                        tv2.text="Amount:      ₹${one}"
                        tv3.text="\nTOTAL:      ₹${total.toString()}"
                        tv.text="Total Items: ${two}"
                    }
                    else{

                    }
                }


        dine.setOnClickListener {
            del=0
            val tax=one.toString().toInt()*0.05
            val dis=0
            val total=tax+one.toString().toInt()+del-dis
            tv6.visibility=View.GONE
            tv5.text="Discount:    ₹${dis}"
            tv4.text="Tax (5%):    ₹${tax.toString()}"
            tv2.text="Amount:      ₹${one}"
            tv3.text="\nTOTAL:      ₹${total.toString()}"
            tv.text="Total Items: ${two}"
        }
        home.setOnClickListener {
            del=100
            val tax=one.toString().toInt()*0.05
            val dis=0
            val total=tax+one.toString().toInt()+del-dis
            tv6.visibility=View.VISIBLE
            tv5.text="Discount:    ₹${dis}"
            tv6.text="Delivery:    ₹${del}"
            tv4.text="Tax (5%):    ₹${tax.toString()}"
            tv2.text="Amount:      ₹${one}"
            tv3.text="\nTOTAL:      ₹${total.toString()}"
            tv.text="Total Items: ${two}"

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }
}

