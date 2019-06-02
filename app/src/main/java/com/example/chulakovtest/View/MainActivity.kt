package com.example.chulakovtest.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.chulakovtest.Model.User
import com.example.chulakovtest.R
import com.example.chulakovtest.adapters.UsersAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recycler.*

class MainActivity : AppCompatActivity(), UsersAdapter.OnItemClickListener {

    private lateinit var navController: NavController
    lateinit var actionBar: ActionBar

    override fun onItemClick(user: User) {
        val args = Bundle()
        args.putParcelable("USER", user)
        navController.navigate(R.id.action_recyclerFragment_to_profileFragment, args)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.recyclerFragment)
        actionBar = supportActionBar!!
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(false)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()

        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        actionBar.setDisplayHomeAsUpEnabled(false)
//        progressBar?.visibility = View.GONE
    }

}
