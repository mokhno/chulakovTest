package com.example.chulakovtest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chulakovtest.Model.User

class UsersAdapter : RecyclerView.Adapter<UsersHolder>() {

    var userList: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UsersHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var view: View = layoutInflater.inflate(R.layout.res_user, p0, false)
        return UsersHolder(view)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(p0: UsersHolder, p1: Int) {
        p0.bind(userList[p1])
    }

    fun addData(users: List<User>){

        userList.addAll(users)
        notifyDataSetChanged()
    }

}

class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {

    lateinit var user: User

    private val login: TextView = view.findViewById(R.id.tv_login)
    private val id: TextView = view.findViewById(R.id.tv_id)


    fun bind(user: User) {
        login.text = user.login
        id.text = user.id.toString()

        this.user = user
    }


}