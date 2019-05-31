package com.example.chulakovtest

import android.content.Context
import android.content.Intent
import android.net.Uri
//import android.os.Build.USER
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.chulakovtest.Model.Profile
import com.example.chulakovtest.Model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fr_profile.*
import kotlinx.android.synthetic.main.res_user.*
import retrofit2.Call
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var user: User
    lateinit var profile: Profile

    companion object {
        fun newInstance(user: User): ProfileFragment {
            var prFragment = ProfileFragment()

            var args = Bundle()
            args.putParcelable("USER", user)
            prFragment.arguments = args
            return prFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("tag", "args notnull")
        user = arguments!!.getParcelable("USER")
        getProfile(user)
        Log.d("tag", "massage" + user.id)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fr_profile, container, false)


    }

    private fun getProfile(user: User) {
        ApiGit.apiService.getProfile(user.login).enqueue(object : retrofit2.Callback<Profile> {
            override fun onFailure(call: Call<Profile>, t: Throwable) {

            }

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {

                profile = response.body()!!

                initViews()


            }


        })

    }

    fun initViews() {
        Picasso.get().load(Uri.parse(profile.avatar_url)).into(imageViewAvatar)
        tv_name.text = profile.name
        loginTv.text = profile.login
        loginTv.setOnClickListener {   val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.html_url))
            startActivity(intent) }
//        tv_html.text = "Ссылка на профиль"
//        tv_html.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.html_url))
//            startActivity(intent)
//        }
        tv_bio.text = profile.bio


        tv_followers.text = "Followers: ${profile.followers}"
        tv_following.text = "Following: ${profile.following}"

    }

}