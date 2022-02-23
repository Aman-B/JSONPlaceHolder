package com.example.jsonplaceholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ID = "id"
private const val NAME = "name"
private const val USER_NAME = "username"
private const val EMAIL = "email"
private const val PHONE = "phone"
private const val WEBSITE = "website"

/**
 * A simple [Fragment] subclass.
 * Use the [UserPostDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserPostDetailsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var postDetailsAdapter: PostDetailsAdapter
    private lateinit var userDetailsView: TextView
    private lateinit var postDetailsView: TextView

    // TODO: Rename and change types of parameters
    private var id: Int? = null
    private var name: String? = null
    private var userName: String? = null
    private var email: String? = null
    private var phone: String? = null
    private var website: String? = null

    private lateinit var viewModel: UserPostDetailsViewModel
    private lateinit var postDetailsList: List<PostModel>
    private var dummyPostModel: PostModel = PostModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID)
            name = it.getString(NAME)
            userName = it.getString(USER_NAME)
            email = it.getString(EMAIL)
            phone = it.getString(PHONE)
            website = it.getString(WEBSITE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_post_details, container, false)

        if (id != null) {
            postDetailsList = mutableListOf(dummyPostModel)
            viewModel = UserPostDetailsViewModel(id!!)
            userDetailsView = rootView.findViewById(R.id.userDetailsView)
            recyclerView = rootView.findViewById(R.id.postDetailsRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            postDetailsAdapter = PostDetailsAdapter(postDetailsList)
            recyclerView.adapter = postDetailsAdapter
            setUserDetails()
            viewModel.postList.observe(
                viewLifecycleOwner,
                Observer { postList -> updatePostDetailsAdapter(postList) })
        } else {
            Toast.makeText(context, "Some error occurred.", Toast.LENGTH_LONG).show()
        }

        return rootView
    }

    private fun setUserDetails() {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" id ").append(id)
        stringBuilder.append(" name ").append(name)
        stringBuilder.append(" username ").append(userName)
        stringBuilder.append("phone ").append(phone)
        stringBuilder.append(" email").append(email)
        stringBuilder.append(" website ").append(website)
        userDetailsView.setText(stringBuilder)
    }

    private fun updatePostDetailsAdapter(postList: List<PostModel>?) {
        if (postList != null) {
            postDetailsList.toMutableList().addAll(postList)
            postDetailsAdapter.updatePostDetailsList(postList)
        }
    }
}