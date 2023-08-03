package com.nppr.royal

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class MenuFragment : Fragment(R.layout.fragment_menu) {
    lateinit var recc: RecyclerView
    lateinit var recc2: RecyclerView
    lateinit var recc3: RecyclerView
    lateinit var recc4: RecyclerView
    lateinit var recc5: RecyclerView
    lateinit var recc6: RecyclerView
    lateinit var recc7: RecyclerView
    lateinit var recc8: RecyclerView
    lateinit var recc9: RecyclerView
    lateinit var recc10: RecyclerView
    lateinit var recc11: RecyclerView

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)



        var starter = mutableListOf<homeModel>()
        var snacks = mutableListOf<homeModel>()
        var bevv = mutableListOf<homeModel>()
        var soup = mutableListOf<homeModel>()
        var south = mutableListOf<homeModel>()
        var chin = mutableListOf<homeModel>()
        var ital = mutableListOf<homeModel>()
        var mainn = mutableListOf<homeModel>()
        var bread = mutableListOf<homeModel>()
        var rice = mutableListOf<homeModel>()
        var desser = mutableListOf<homeModel>()
        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Starters").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                starter.add(homeModel(id,img, name, price, type))
            }
        }.addOnCompleteListener {
            val layoutManager = LinearLayoutManager(context)
            recc = itemView.findViewById(R.id.rv_start)
            recc.layoutManager = layoutManager
            val adapter = homeAdapter(starter)
            recc.adapter = adapter
        }

        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Snacks").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                snacks.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {


            val layoutManager2 = LinearLayoutManager(context)

            recc2 = itemView.findViewById(R.id.rv_snacks)
            recc2.layoutManager = layoutManager2
            val adapter2 = homeAdapter(snacks)
            recc2.adapter = adapter2
        }


        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Soups").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                soup.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {


            val layoutManager3 = LinearLayoutManager(context)
            recc3 = itemView.findViewById(R.id.rv_soups)
            recc3.layoutManager = layoutManager3
            val adapter3 = homeAdapter(soup)
            recc3.adapter = adapter3
        }


        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Beverages").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                bevv.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {

            val layoutManager4 = LinearLayoutManager(context)
            recc4 = itemView.findViewById(R.id.rv_beverr)
            recc4.layoutManager = layoutManager4
            val adapter4 = homeAdapter(bevv)
            recc4.adapter = adapter4
        }


        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","South").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                south.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {

            val layoutManager5 = LinearLayoutManager(context)
            recc5 = itemView.findViewById(R.id.rv_south)
            recc5.layoutManager = layoutManager5
            val adapter5 = homeAdapter(south)
            recc5.adapter = adapter5
        }



        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Chinese").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                chin.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {

            val layoutManager6 = LinearLayoutManager(context)
            recc6 = itemView.findViewById(R.id.rv_chinese)
            recc6.layoutManager = layoutManager6
            val adapter6 = homeAdapter(chin)
            recc6.adapter = adapter6
        }


        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Italian").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                ital.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {

            val layoutManager7 = LinearLayoutManager(context)
            recc7 = itemView.findViewById(R.id.rv_italian)
            recc7.layoutManager = layoutManager7
            val adapter7 = homeAdapter(ital)
            recc7.adapter = adapter7

        }



        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Main Course").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                mainn.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {

            val layoutManager8 = LinearLayoutManager(context)
            recc8 = itemView.findViewById(R.id.rv_mainn)
            recc8.layoutManager = layoutManager8
            val adapter8 = homeAdapter(mainn)
            recc8.adapter = adapter8
        }



        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Bread").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                bread.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {

            val layoutManager9 = LinearLayoutManager(context)
            recc9 = itemView.findViewById(R.id.rv_breads)
            recc9.layoutManager = layoutManager9
            val adapter9 = homeAdapter(bread)
            recc9.adapter = adapter9
        }


        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Rice").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                rice.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {

            val layoutManager10 = LinearLayoutManager(context)
            recc10 = itemView.findViewById(R.id.rv_rice)
            recc10.layoutManager = layoutManager10
            val adapter10 = homeAdapter(rice)
            recc10.adapter = adapter10
        }


        FirebaseFirestore.getInstance().collection("Food").whereEqualTo("Type","Dessert").get().addOnSuccessListener {
            for(doc in it) {
                var id= doc.id.toString()
                var name = doc.data["Name"].toString()
                var price = doc.data["Price"].toString()
                var type = doc.data["Type"].toString()
                var img = doc.data["Image"].toString()
                desser.add(homeModel(id,img, name, price, type))
                Log.i("img",img)
            }
        }.addOnCompleteListener {

            val layoutManager11 = LinearLayoutManager(context)
            recc11 = itemView.findViewById(R.id.rv_dess)
            recc11.layoutManager = layoutManager11
            val adapter11 = homeAdapter(desser)
            recc11.adapter = adapter11

        }

    }
}