package com.nppr.royal

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nppr.royal.databinding.ActivityHomeBinding

class home : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)
/*
        binding.appBarHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val iv:ImageView=binding.appBarHome.ivSignOut
        val txxUser:TextView=binding.tvoffUser//navView.findViewById(R.id.tvoff_user)
        val txxEmail:TextView=binding.tvoffEmail

        val ix: androidx.appcompat.widget.Toolbar =binding.appBarHome.toolbar

        //navView.nav_signOut
        val loc:ImageView=binding.appBarHome.ivLoca
        val curr:ImageView=binding.appBarHome.ivHiss
        val carr:ImageView=binding.appBarHome.ivCart
        val home:ImageView=binding.appBarHome.ivHome
        //Toast.makeText(this,"${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}",Toast.LENGTH_LONG).show()
        txxEmail.text=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()

        val db=FirebaseFirestore.getInstance().collection("Users").whereEqualTo("email",FirebaseAuth.getInstance().currentUser!!.email.toString()).get().addOnSuccessListener {
            for (doc in it){
                if(doc.data["name"]!=null){
                    //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
                    txxUser.text=doc.data["name"].toString()
                }
            }
        }
        /*whereEqualTo("mobile_number",
                            FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {
                            for (document in it) {
                                //if (document.data["cartFinal"] != null) {

                                    db.collection("Users").document(document.id)
                                        .set(userMap, SetOptions.merge()).addOnSuccessListener {
                                        }
                                //}
                            }*/

        iv.setOnClickListener {
            replaceFragment(signOutFragment())
            ix.setTitle("Greetings from AirO'Drive")
        }
        home.setOnClickListener {
            replaceFragment(HomeFragment())
            ix.setTitle("Home")
        }
        loc.setOnClickListener {

            replaceFragment(MenuFragment())
            ix.setTitle("Menu")
        }
        carr.setOnClickListener {

            replaceFragment(cartFragment())
            ix.setTitle("Cart")
        }
        curr.setOnClickListener {
            replaceFragment(OrdersFragment())
            ix.setTitle("Your Orders")
        }
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_menu, R.id.nav_history, R.id.cartt,R.id.nav_signOut
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_content_home,fragment)
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmentTransaction.commit()

    }

    private fun replaceFragmentMenu(fragment : Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null)
        fragmentTransaction.replace(R.id.nav_host_fragment_content_home,fragment)
        fragmentTransaction.commit()
    }
}