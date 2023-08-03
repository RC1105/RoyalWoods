package com.nppr.royal

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.nppr.royal.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        var cnt=0
        val one: TextView =itemView.findViewById(R.id.tv_d1)
        val two: TextView =itemView.findViewById(R.id.tv_d2)
        val three: TextView =itemView.findViewById(R.id.tv_d3)
        val four: TextView =itemView.findViewById(R.id.tv_d4)
        val pic:ImageView= itemView.findViewById(R.id.iv_iv)
        val info: TextView =itemView.findViewById(R.id.tv_info)

        val lss=listOf<String>("Royal Woods Welcomes You!","Get your food delivered in minutes!","Get Food Recommendations, Calorie values","Avail coupons, discount & much more")

        var arr=ArrayList<Int>()
        arr.add(R.drawable.wel)
        arr.add(R.drawable.quick)
        arr.add(R.drawable.food)
        arr.add(R.drawable.coupe)

        pic.setImageResource(arr[0])

        two.setTextColor(Color.parseColor("#ff0000"))

        one.setTextColor(Color.parseColor("#0000FF"))
        three.setTextColor(Color.parseColor("#ff0000"))
        four.setTextColor(Color.parseColor("#ff0000"))
/*        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Snacks").get().addOnSuccessListener {it->
            for(doc in it){
//                Toast.makeText(context,${doc.get("Image")})
                //info.text=
                Log.i("hih",doc.get("Image").toString())
            }
        }
*/
        pic.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->
                    {
                        cnt++
                        cnt=cnt%lss.size
                        if(cnt==lss.size-1){
                            pic.setImageResource(arr[cnt])
                            info.text=lss[cnt]
                        }
                        else{
                            pic.setImageResource(arr[cnt])
                            info.text=lss[cnt]
                            //cnt++
                        }

                        if(cnt==0){

                            two.setTextColor(Color.parseColor("#ff0000"))

                            one.setTextColor(Color.parseColor("#0000FF"))
                            three.setTextColor(Color.parseColor("#ff0000"))
                            four.setTextColor(Color.parseColor("#ff0000"))
                        }

                        if(cnt==1){

                            three.setTextColor(Color.parseColor("#ff0000"))
                            two.setTextColor(Color.parseColor("#0000FF"))

                            one.setTextColor(Color.parseColor("#ff0000"))
                            four.setTextColor(Color.parseColor("#ff0000"))
                        }

                        if(cnt==2){

                            three.setTextColor(Color.parseColor("#0000FF"))

                            two.setTextColor(Color.parseColor("#ff0000"))

                            one.setTextColor(Color.parseColor("#ff0000"))
                            four.setTextColor(Color.parseColor("#ff0000"))

                        }
                        if(cnt==3){

                            three.setTextColor(Color.parseColor("#ff0000"))

                            two.setTextColor(Color.parseColor("#ff0000"))

                            one.setTextColor(Color.parseColor("#ff0000"))
                            four.setTextColor(Color.parseColor("#0000ff"))

                        }
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
}