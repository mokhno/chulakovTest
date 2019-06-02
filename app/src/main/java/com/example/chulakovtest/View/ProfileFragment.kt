package com.example.chulakovtest.View

import android.content.Intent
import android.net.Uri
//import android.os.Build.USER
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chulakovtest.network.RestClient
import com.example.chulakovtest.Model.Profile
import com.example.chulakovtest.Model.User
import com.example.chulakovtest.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var user: User
    lateinit var profile: Profile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments!!.getParcelable("USER")
        getProfile(user)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    private fun getProfile(user: User) {

        RestClient.githubApi.getProfile(user.login).enqueue(object : retrofit2.Callback<Profile> {
            override fun onFailure(call: Call<Profile>, t: Throwable) {
            }

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                profile = response.body()!!
                initViews()
            }
        })

    }

    fun initViews() {
        Picasso.get().load(Uri.parse(profile.avatarUrl)).into(imageViewAvatar)
        tv_name.text = profile.name
        loginTv.text = profile.login
        loginTv.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl))
            startActivity(intent)
        }
        tv_bio.text = profile.bio
        tv_followers.text = "Followers: ${profile.followers}"
        tv_following.text = "Following: ${profile.following}"
    }

}