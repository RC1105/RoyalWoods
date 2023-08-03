package com.nppr.royal

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.squareup.picasso.Picasso
import java.util.Objects


class homeAdapter(val orrList: List<homeModel>): RecyclerView.Adapter<homeAdapter.MainViewHolder>() {
    private lateinit var contexx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeAdapter.MainViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.all_list_item,parent, false)
        contexx = parent.context
        return MainViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: homeAdapter.MainViewHolder, position: Int) {
        val curr=orrList[position]
        holder.itemName.text=curr.pdtName
        holder.itemPpu.text=curr.price.toString()

        var op=curr.price!!.toInt()*curr.qty.toString().toInt()
        holder.itemTotal.text=op.toString()
        holder.itemQty.text=curr.qty.toString()
        holder.add.setOnClickListener{
            holder.add.visibility=View.GONE
            holder.more.visibility=View.VISIBLE
            holder.remm.visibility=View.VISIBLE
            holder.subtot.visibility=View.VISIBLE
            holder.remover.visibility=View.VISIBLE
            holder.atc.visibility=View.VISIBLE
            holder.addedtc.visibility=View.GONE
        }
        holder.remover.setOnClickListener {
            holder.add.visibility=View.VISIBLE
            holder.more.visibility=View.GONE
            holder.remm.visibility=View.GONE
            holder.subtot.visibility=View.INVISIBLE

            curr.qty=(1)
            var op=curr.price!!.toInt()*curr.qty.toString().toInt()
            holder.itemTotal.text=op.toString()
            holder.itemQty.text=curr.qty.toString()
        }
        holder.plus.setOnClickListener {
            curr.qty=(curr.qty.toString().toInt()+1)
            var op=curr.price!!.toInt()*curr.qty.toString().toInt()
            holder.itemTotal.text=op.toString()
            holder.itemQty.text=curr.qty.toString()
        }
        holder.minus.setOnClickListener {
            if(curr.qty.toString().toInt()>1){

                curr.qty=(curr.qty.toString().toInt()-1)
                var op=curr.price!!.toInt()*curr.qty.toString().toInt()
                holder.itemTotal.text=op.toString()
                holder.itemQty.text=curr.qty.toString()
            }

        }
        holder.atc.setOnClickListener {
            var hashher= mutableMapOf(
                "Name" to "dfd"
            )
            var doccie:String=""
            FirebaseFirestore.getInstance().collection("Users").whereEqualTo("Phone",FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()).get().addOnSuccessListener {
                Log.e("hahah","yoyo ${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}")
                //.toObjects()
                for(doc in it){
                    doccie=doc.id.toString()
                    Log.e("hahah","${doc.data["Phone"]}")
                    Log.e("hahah","${doc.data["Cart"]}")
                    hashher=doc.data["Cart"] as MutableMap<String, String>
                    if(hashher.containsKey("${curr.id}")){
                        hashher.put("${curr.id}",curr.qty.toString())
                    }
                    hashher.putIfAbsent("${curr.id}","${curr.qty}")

                    Log.e("hahah","${hashher}")
                //hashher=hash
                    //val arr = (doc.data["Cart"] as HashMap)
                    //arr.add(CartModel(curr.id))

                }
            }.addOnCompleteListener {
                var userMap = mutableMapOf(
                    "Cart" to hashher
                )
                FirebaseFirestore.getInstance().collection("Users").document(doccie).set(userMap,
                    SetOptions.merge()).addOnSuccessListener {
                        holder.addedtc.visibility=View.VISIBLE
                        holder.remover.visibility=View.GONE
                        holder.atc.visibility=View.GONE
                        holder.subtot.visibility=View.INVISIBLE
                        holder.more.visibility=View.GONE
                        holder.add.visibility=View.VISIBLE
                        Handler().postDelayed({

                            holder.addedtc.visibility=View.GONE
                        },3000)
                        //Log.e("hahah","Safalta")
                }
            }
            //.set(userMap, SetOptions.merge()).addOnSuccessListener {



        }
        Picasso
            .get()
            .load(curr.image.toString())
            .into(holder.img)

        //Glide.with(contexx).load(curr.img).into(holder.img)
    }


    override fun getItemCount(): Int {
        return orrList.size
    }
    inner class MainViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemName: TextView =itemView.findViewById(R.id.product_name)
        val itemPpu: TextView =itemView.findViewById(R.id.tv_pricePerUnit)
        val itemTotal: TextView =itemView.findViewById(R.id.tv_totalPrice)
        val itemQty: TextView =itemView.findViewById(R.id.tv_qtyNo)
        val img: ImageView =itemView.findViewById(R.id.product_image)
        val add: LinearLayout =itemView.findViewById(R.id.ll_add)
        val more: LinearLayout =itemView.findViewById(R.id.ll_more)
        val remm: LinearLayout=itemView.findViewById(R.id.ll_Remove)
        val subtot: LinearLayout=itemView.findViewById(R.id.ll_subb)
        val remover: TextView=itemView.findViewById(R.id.tv_btnRemove)
        val plus: TextView=itemView.findViewById(R.id.tv_btnGreen)
        val atc: TextView=itemView.findViewById(R.id.tv_btnAddToCart)
        val addedtc: TextView=itemView.findViewById(R.id.tv_btnAddedToCart)
        val minus: TextView=itemView.findViewById(R.id.tv_btnRed)

    }
}


/*
class orderAdapter(val orderList: List<ordersData>): RecyclerView.Adapter<orderAdapter.MainViewHolder>()  {

}
*/