package com.example.jsonplaceholder.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.adapters.PostDetailsAdapter
import com.example.jsonplaceholder.data.api.PostEndpoints
import com.example.jsonplaceholder.data.api.RetrofitInstance
import com.example.jsonplaceholder.data.models.PostModel
import com.example.jsonplaceholder.data.models.UserModel
import com.example.jsonplaceholder.viewModels.UserPostDetailsViewModel

/**
 * Shows user details and user's post details.
 */
class UserPostDetailsFragment : Fragment() {
    private lateinit var postEndpointInstance: PostEndpoints
    private lateinit var postDetailsAdapter: PostDetailsAdapter
    private val LOGTAG: String = "UserPostDetails"
    private lateinit var recyclerView: RecyclerView
    private lateinit var userID: TextView
    private lateinit var name: TextView
    private lateinit var userName: TextView
    private lateinit var userPhone: TextView
    private lateinit var userEmail: TextView
    private lateinit var userWebsite: TextView
    private lateinit var userPostDetailsViewModel: UserPostDetailsViewModel
    private var postDetailsList: ArrayList<PostModel> = ArrayList()

    private lateinit var userModel: UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userModel = it.getSerializable("userDetails") as UserModel
            Log.i(LOGTAG, " userModel $userModel")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_post_details, container, false)

        userID = rootView.findViewById(R.id.user_id)
        name = rootView.findViewById(R.id.name)
        userName = rootView.findViewById(R.id.user_name)
        userPhone = rootView.findViewById(R.id.user_phone)
        userEmail = rootView.findViewById(R.id.user_email)
        userWebsite = rootView.findViewById(R.id.user_website)

        recyclerView = rootView.findViewById(R.id.post_details_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        postDetailsAdapter = PostDetailsAdapter(postDetailsList)
        recyclerView.adapter = postDetailsAdapter

        postEndpointInstance = RetrofitInstance.getInstance()?.create(PostEndpoints::class.java)!!

        userPostDetailsViewModel = UserPostDetailsViewModel(userModel.id, postEndpointInstance)
        userPostDetailsViewModel.getPostList.observe(
            viewLifecycleOwner,
            { postList -> updatePostDetailsAdapter(postList) })
//        setPostDetailsAdapterForRecyclerView()
        setUserDetailsInUI()

        return rootView
    }


    /**
     * Sets user details in userDetailsView; using the values in userModel
     */
    private fun setUserDetailsInUI() {
        userID.text = "ID :  ${userModel.id}"
        name.text = "Name :  ${userModel.name}"
        userName.text = "Username :  ${userModel.username}"
        userPhone.text = "Phone No :  ${userModel.phone}"
        userEmail.text = userModel.email
        userWebsite.text = userModel.website

    }

    /**
     * Updates the postDetailsAdapter with postList returned by the remote source.
     * @param postList
     */
    private fun updatePostDetailsAdapter(postList: List<PostModel>?) {
        if (postList != null) {
            postDetailsAdapter.updatePostDetailsList(postList)
        } else {
            Log.e(LOGTAG, "PostList returned from remote source is null.")
        }
    }
}