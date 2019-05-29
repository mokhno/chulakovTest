package com.example.chulakovtest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.chulakovtest.Model.User
import kotlinx.android.synthetic.main.fr_recycler.*
import retrofit2.Call
import retrofit2.Response
import android.nfc.tech.MifareUltralight.PAGE_SIZE


class RecyclerFragment : Fragment() {
    private lateinit var users: List<User>

    companion object {
        fun newInstance(): RecyclerFragment {

            return RecyclerFragment()
        }
    }

    var userAdapter: UsersAdapter = UsersAdapter()
    var layoutManagerUser = LinearLayoutManager(activity)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fr_recycler, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler.layoutManager = layoutManagerUser
        recycler.adapter = userAdapter

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManagerUser.childCount
                val totalItemCount = layoutManagerUser.itemCount
                val firstVisibleItemPosition = layoutManagerUser.findFirstVisibleItemPosition()


//                if (!isLoading && !isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE
                ) {
                    getAllUsers(users.last().id)
                }
//                }
            }
        })
        getAllUsers(0)
    }


    private fun getAllUsers(i: Int) {
        ApiGit.apiService.getUsers(i).enqueue(object : retrofit2.Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("tag", "fail")


            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("tag", "response")
                
                users = response.body()!!

                userAdapter.addData(users)

            }


        })
    }

}