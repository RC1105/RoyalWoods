package com.nppr.royal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


class signOutFragment : Fragment(R.layout.fragment_sign_out) {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        Handler().postDelayed({
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, Intro::class.java)
            startActivity(intent)
        },1500)
    }
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view=inflater.inflate(R.layout.fragment_sign_out, container, false)
        return view
   }
}