package com.example.chulakovtest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var users: MutableList<User> = mutableListOf()
    private var isLoading = false
    private lateinit var mListener: UsersAdapter.OnItemClickListener


    companion object {
        fun newInstance(): RecyclerFragment {

            return RecyclerFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context!!)
        if (context is UsersAdapter.OnItemClickListener) {
            mListener = context
        }
    }

    var userAdapter: UsersAdapter = UsersAdapter()
    lateinit var layoutManagerUser: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAllUsers(0)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fr_recycler, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManagerUser = LinearLayoutManager(activity)
        recycler.layoutManager = layoutManagerUser

        recycler.adapter = userAdapter

        userAdapter.setListener(mListener)


        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

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
//        getAllUsers(0)
    }


    private fun getAllUsers(i: Int) {
        Log.d("tag", "Getallusers")
        isLoading = true
        ApiGit.apiService.getUsers(i).enqueue(object : retrofit2.Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("tag", "fail")


            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("tag", "response")

                users = response.body()!!.toMutableList()

                userAdapter.addData(users)
                isLoading = false
            }


        })
    }

    override fun onDetach() {
//        mListener = null
        super.onDetach()
    }

}