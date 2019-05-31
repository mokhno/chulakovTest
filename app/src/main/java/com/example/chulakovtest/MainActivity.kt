package com.example.chulakovtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.chulakovtest.Model.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), UsersAdapter.OnItemClickListener {

    var recyclerFragment:RecyclerFragment = RecyclerFragment.newInstance()
private lateinit var navController: NavController
    override fun onItemClick(user: User) {
//supportFragmentManager.beginTransaction().hide(recyclerFragment)
//
//        supportFragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_left,R.animator.slide_in_right).replace(R.id.container, ProfileFragment.newInstance(user)).addToBackStack(null).commit()
        var args = Bundle()
        args.putParcelable("USER", user)
        navController.navigate(R.id.profileFragment,args)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager.beginTransaction().add(R.id.container, recyclerFragment).commit()
//
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.recyclerFragment)
    }


}
