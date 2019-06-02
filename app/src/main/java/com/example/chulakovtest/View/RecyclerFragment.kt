package com.example.chulakovtest.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chulakovtest.Model.User
import kotlinx.android.synthetic.main.fragment_recycler.*
import retrofit2.Call
import retrofit2.Response
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.util.Log
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chulakovtest.network.RestClient
import com.example.chulakovtest.R
import com.example.chulakovtest.adapters.UsersAdapter


class RecyclerFragment : Fragment() {
    private var users: MutableList<User> = mutableListOf()
    private var isLoading = false
    private var progressBar: ProgressBar? = null

    private lateinit var mListener: UsersAdapter.OnItemClickListener
    var userAdapter: UsersAdapter = UsersAdapter()
    lateinit var layoutManagerUser: GridLayoutManager
    override fun onAttach(context: Context) {
        super.onAttach(context!!)
        if (context is UsersAdapter.OnItemClickListener) {
            mListener = context
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAllUsers(0)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManagerUser = GridLayoutManager(activity, 2)
        recycler.layoutManager = layoutManagerUser
        recycler.adapter = userAdapter
        progressBar = view?.findViewById(R.id.progressBar)

        userAdapter.setListener(mListener)

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManagerUser.childCount
                val totalItemCount = layoutManagerUser.itemCount
                val firstVisibleItemPosition = layoutManagerUser.findFirstVisibleItemPosition()


                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE
                    ) {
                        getAllUsers(users.last().id)
                    }
                }
            }
        })
    }

    private fun getAllUsers(i: Int) {
        isLoading = true
        progressBar?.visibility = View.VISIBLE
        RestClient.githubApi.getUsers(i).enqueue(object : retrofit2.Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                users = response.body()!!.toMutableList()
                userAdapter.addData(users)
                isLoading = false

                progressBar?.visibility = View.GONE
            }


        })
    }


}