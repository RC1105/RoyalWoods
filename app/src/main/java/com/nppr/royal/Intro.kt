package com.nppr.royal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class Intro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val pic:ImageView=findViewById(R.id.iv_picc)

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(Intent(this, home::class.java))
            finish()
        }



        val lsg: TextView =findViewById(R.id.lgs)
        val skip: TextView =findViewById(R.id.tv_skip)
        val one: TextView =findViewById(R.id.tv_d1)
        val two: TextView =findViewById(R.id.tv_d2)
        val three: TextView =findViewById(R.id.tv_d3)
        val four: TextView =findViewById(R.id.tv_d4)

        val nexx:TextView=findViewById(R.id.tv_next)

        val info:TextView=findViewById(R.id.tv_info)
        val lss=listOf<String>("Royal Woods Welcomes You!","Get your food delivered in minutes!","Get Food Recommendations, Calorie values","Avail coupons, discount & much more")

        var arr=ArrayList<Int>()
        arr.add(R.drawable.wel)
        arr.add(R.drawable.quick)
        arr.add(R.drawable.food)
        arr.add(R.drawable.coupe)
        var cnt=0
        pic.setImageResource(arr[0])
/*
        Picasso
            .get()
            .load("https://firebasestorage.googleapis.com/v0/b/royalwoods-3d132.appspot.com/o/Screenshot%202023-07-27%20225757.png?alt=media&token=cdcc1977-be3d-4404-bbcf-54234d1566ce")
            .into(pic)

 */
        info.text=lss[0]
        var runnable: Runnable? = null
        one.setTextColor(Color.parseColor("#0000FF"))

        pic.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->
                    {
                        cnt++
                        cnt=cnt%lss.size
                        if(cnt==lss.size-1){
                            skip.visibility=View.INVISIBLE
                            lsg.visibility=View.VISIBLE

//
                            info.text=lss[cnt]

                            pic.setImageResource(arr[cnt])
                        }
                        else{

                            info.text=lss[cnt]

                            pic.setImageResource(arr[cnt])
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
        nexx.setOnClickListener {

            cnt++
            if(cnt==lss.size-1){
                skip.visibility=View.INVISIBLE
                lsg.visibility=View.VISIBLE

//
                info.text=lss[cnt]

                pic.setImageResource(arr[cnt])
            }
            else if(cnt==lss.size){
                val intent= Intent(this, otpp::class.java)
                startActivity(intent)
                finish()
            }
            else{

                info.text=lss[cnt]

                pic.setImageResource(arr[cnt])
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
        skip.setOnClickListener {

            val intent= Intent(this, otpp::class.java)
            startActivity(intent)
            finish()
        }
        lsg.setOnClickListener {

            val intent= Intent(this, otpp::class.java)
            startActivity(intent)
            finish()
        }
    }
}