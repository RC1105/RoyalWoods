package com.nppr.royal

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.squareup.picasso.Picasso


class cartAdapter(val orrList: MutableList<homeModel>, private val onAudi: cartFragment): RecyclerView.Adapter<cartAdapter.MainViewHolder>() {
    private lateinit var contexx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cartAdapter.MainViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.carter,parent, false)
        contexx = parent.context
        return MainViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: cartAdapter.MainViewHolder, position: Int) {
        val curr=orrList[position]
        holder.itemName.text=curr.pdtName
        holder.itemPpu.text=curr.price
        var op=curr.price!!.toInt()*curr.qty.toString().toInt()
        holder.itemTotal.text=op.toString()
        holder.itemQty.text=curr.qty.toString()
        holder.type.text=curr.type.toString()
        Picasso
            .get()
            .load(curr.image.toString())
            .into(holder.img)


        holder.plus.setOnClickListener {
            curr.qty=(curr.qty.toString().toInt()+1)
            var op=curr.price!!.toInt()*curr.qty.toString().toInt()
            holder.itemTotal.text=op.toString()
            holder.itemQty.text=curr.qty.toString()
            var ans=total()
            onAudi.onChangeQty(ans)

        }
        holder.minus.setOnClickListener {
            if(curr.qty.toString().toInt()>1){

                curr.qty=(curr.qty.toString().toInt()-1)
                var op=curr.price!!.toInt()*curr.qty.toString().toInt()
                holder.itemTotal.text=op.toString()
                holder.itemQty.text=curr.qty.toString()
                var ans=total()
                onAudi.onChangeQty(ans)
            }

        }
        holder.remove.setOnClickListener {
            var uka= mutableMapOf(
                "Name" to "dfd"
            )
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
                    Log.e("hahah","${doc.data["Phone"]}")
                    Log.e("hahah","${doc.data["Cart"]}")
                    var hashher=doc.data["Cart"] as MutableMap<String, String>
                    if(hashher.containsKey("${curr.id}")){
                        hashher.remove("${curr.id}")
                        uka=hashher
                    }
                }
            }.addOnCompleteListener {

                Log.i("hahah","this is the hashh ${uka.toString()}" )
                var userMap = mutableMapOf(
                    "Name" to name,
                    "Cart" to uka,
                    "Phone" to phone
                )
                FirebaseFirestore.getInstance().collection("Users").document(doccie).set(userMap).addOnSuccessListener {
                    Log.i("hahah","safaltaa")
                }
                orrList[position].qty=0
                var ans=total()
                onAudi.onChangeQty(ans)

                orrList.removeAt(position)
                notifyItemRemoved(position)
                notifyDataSetChanged()
                notifyItemRangeChanged(position,orrList.size)

            }
        }
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
        val type:TextView=itemView.findViewById(R.id.tv_type)
        val plus: TextView=itemView.findViewById(R.id.tv_btnGreen)
        val minus: TextView=itemView.findViewById(R.id.tv_btnRed)
        val remove: ImageView=itemView.findViewById(R.id.tv_btnRemove)

    }
    fun total():Int{
        //var i:Int=0
        val n=itemCount
        var cost=0
        for (i in 0..n-1){
            cost+=(orrList[i].price.toString().toInt()*orrList[i].qty.toString().toInt())

        }
        return cost
    }
    public interface audi{
        fun onChangeQty(ans:Int): Int {
            Log.i("ocq","${ans}")
            return ans
        }
    }

}


/*
class orderAdapter(val orderList: List<ordersData>): RecyclerView.Adapter<orderAdapter.MainViewHolder>()  {

}
*/

