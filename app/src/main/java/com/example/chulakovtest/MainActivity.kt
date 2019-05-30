package com.example.chulakovtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.chulakovtest.Model.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), UsersAdapter.OnItemClickListener {

    var recyclerFragment:RecyclerFragment = RecyclerFragment.newInstance()

    override fun onItemClick(user: User) {
//supportFragmentManager.beginTransaction().hide(recyclerFragment)
        supportFragmentManager.beginTransaction().replace(R.id.container, ProfileFragment.newInstance(user)).addToBackStack(null).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.container, recyclerFragment).commit()

    }


}
