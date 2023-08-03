package com.nppr.royal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class orrAdapter(val orrList: List<orrData2>): RecyclerView.Adapter<orrAdapter.MainViewHolder>() {
    private lateinit var contexx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): orrAdapter.MainViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.recc,parent, false)
        contexx = parent.context
        return MainViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: orrAdapter.MainViewHolder, position: Int) {
        val curr=orrList[position]
        holder.addr.text=curr.addr
        holder.dt.text=curr.dt
        holder.qty.text=curr.qty
        holder.mode.text=curr.mode
        holder.price.text=curr.price
        holder.token.text=curr.token
        if(curr.mode=="DINE IN"){
            holder.viewer.visibility=View.GONE
        }
        /*
        holder.itemName.text=curr.itemName
        holder.itemPpu.text=curr.pricePerUnit.toString()
        var op=curr.pricePerUnit!!*curr.qty.toString().toInt()
        holder.itemTotal.text=op.toString()
        holder.itemQty.text=curr.qty.toString()*/
        //holder.img.setImageResource(R.drawable.rice)
        //Glide.with(contexx).load(curr.img).into(holder.img)
    }


    override fun getItemCount(): Int {
        return orrList.size
    }
    inner class MainViewHolder(val itemView: View):RecyclerView.ViewHolder(itemView) {
        val mode:TextView=itemView.findViewById(R.id.tv_Mode)
        val price:TextView=itemView.findViewById(R.id.tv_price)
        val qty:TextView=itemView.findViewById(R.id.tv_qty)
        val token:TextView=itemView.findViewById(R.id.tv_token)
        val dt:TextView=itemView.findViewById(R.id.tv_dt)
        val addr:TextView=itemView.findViewById(R.id.tv_addr)
        val viewer:LinearLayout=itemView.findViewById(R.id.gayab)



    }
}


/*
class orderAdapter(val orderList: List<ordersData>): RecyclerView.Adapter<orderAdapter.MainViewHolder>()  {

}
*/