package com.example.chulakovtest

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.chulakovtest.Model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fr_profile.*

class UsersAdapter : RecyclerView.Adapter<UsersHolder>() {

    var userList: MutableList<User> = mutableListOf()
    private lateinit var listener: OnItemClickListener

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
        p0.setListener(listener)
    }

    fun addData(users: List<User>) {
        userList.addAll(users)

        notifyDataSetChanged()
    }

    fun setListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }


    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

}

class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {

    lateinit var user: User

    private val login: TextView = view.findViewById(R.id.tv_login)
    private val id: TextView = view.findViewById(R.id.tv_id)
    private val avatar: ImageView = view.findViewById(R.id.avatar_image)


    fun bind(user: User) {
        login.text = user.login
        id.text = user.id.toString()
        Picasso.get().load(user.avatar_url).transform(RoundedCornersTransform()).into(avatar)
        this.user = user
    }

    fun setListener(listener: UsersAdapter.OnItemClickListener) {
        itemView.setOnClickListener { listener.onItemClick(user) }

    }

}