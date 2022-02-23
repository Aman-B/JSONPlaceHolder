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
import com.example.jsonplaceholder.*
import com.example.jsonplaceholder.adapters.PostDetailsAdapter
import com.example.jsonplaceholder.data.model.PostModel
import com.example.jsonplaceholder.data.model.UserModel
import com.example.jsonplaceholder.viewModels.UserPostDetailsViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [UserPostDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserPostDetailsFragment : Fragment() {
    private val LOGTAG: String = "UserPostDetails"
    private lateinit var recyclerView: RecyclerView
    private lateinit var postDetailsAdapter: PostDetailsAdapter
    private lateinit var userDetailsView: TextView
    private lateinit var viewModel: UserPostDetailsViewModel
    private lateinit var postDetailsList: List<PostModel>
    private var dummyPostModel: PostModel = PostModel()

    private lateinit var userModel: UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userModel = it.getSerializable("userDetails") as UserModel
            Log.i(LOGTAG, " usermodel $userModel")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_post_details, container, false)
        postDetailsList = mutableListOf(dummyPostModel)
        viewModel = UserPostDetailsViewModel(userModel.id)
        userDetailsView = rootView.findViewById(R.id.userDetailsView)
        recyclerView = rootView.findViewById(R.id.postDetailsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        postDetailsAdapter = PostDetailsAdapter(postDetailsList)
        recyclerView.adapter = postDetailsAdapter
        setUserDetails()
        viewModel.postList.observe(
            viewLifecycleOwner,
            { postList -> updatePostDetailsAdapter(postList) })

        return rootView
    }

    private fun setUserDetails() {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" id : ").append(userModel.id).append("\n")
        stringBuilder.append(" name : ").append(userModel.name).append("\n")
        stringBuilder.append(" username : ").append(userModel.username).append("\n")
        stringBuilder.append(" phone : ").append(userModel.phone).append("\n")
        stringBuilder.append(" email : ").append(userModel.email).append("\n")
        stringBuilder.append(" website : ").append(userModel.website)
        userDetailsView.text = stringBuilder
    }

    private fun updatePostDetailsAdapter(postList: List<PostModel>?) {
        if (postList != null) {
            postDetailsList.toMutableList().addAll(postList)
            postDetailsAdapter.updatePostDetailsList(postList)
        }
    }
}