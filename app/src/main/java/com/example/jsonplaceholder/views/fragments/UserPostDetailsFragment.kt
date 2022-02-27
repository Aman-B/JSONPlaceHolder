package com.example.jsonplaceholder.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.adapters.PostDetailsAdapter
import com.example.jsonplaceholder.data.api.PostEndpoints
import com.example.jsonplaceholder.data.api.RetrofitInstance
import com.example.jsonplaceholder.data.models.Post
import com.example.jsonplaceholder.data.models.User
import com.example.jsonplaceholder.viewModels.UserPostDetailsViewModel

/**
 * Shows user details and user's post details.
 */
class UserPostDetailsFragment : Fragment() {
    private val LOGTAG: String = "UserPostDetails"
    private var postEndpointInstance: PostEndpoints? = null
    private lateinit var postDetailsAdapter: PostDetailsAdapter
    private lateinit var postDetailsRecyclerView: RecyclerView
    private lateinit var userID: TextView
    private lateinit var name: TextView
    private lateinit var userName: TextView
    private lateinit var userPhone: TextView
    private lateinit var userEmail: TextView
    private lateinit var userWebsite: TextView
    private lateinit var userPostDetailsViewModel: UserPostDetailsViewModel
    private lateinit var progressBar: ProgressBar
    private var postDetailsList: ArrayList<Post> = ArrayList()

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable("userDetails") as User
            Log.i(LOGTAG, " userModel $user")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_post_details, container, false)

        progressBar = rootView.findViewById(R.id.progress_bar)
        userID = rootView.findViewById(R.id.user_id)
        name = rootView.findViewById(R.id.name)
        userName = rootView.findViewById(R.id.user_name)
        userPhone = rootView.findViewById(R.id.user_phone)
        userEmail = rootView.findViewById(R.id.user_email)
        userWebsite = rootView.findViewById(R.id.user_website)

        postDetailsRecyclerView = rootView.findViewById(R.id.post_details_recyclerView)
        postDetailsRecyclerView.layoutManager = LinearLayoutManager(context)
        postDetailsAdapter = PostDetailsAdapter(postDetailsList)
        postDetailsRecyclerView.adapter = postDetailsAdapter

        postEndpointInstance = RetrofitInstance.getInstance()?.create(PostEndpoints::class.java)
        if (postEndpointInstance != null) {
            userPostDetailsViewModel = UserPostDetailsViewModel(user.id, postEndpointInstance!!)
            userPostDetailsViewModel.getPostList.observe(
                viewLifecycleOwner,
                { postList -> updateUI(postList) })
        } else {
            Log.e(LOGTAG, "PostEndpoint not created successfully.")
        }

        setUserDetailsInUI(user)

        return rootView
    }

    /**
     * Updates the UI with postList and hides progressbar
     * @param postList
     */
    private fun updateUI(postList: List<Post>?) {
        updatePostDetailsAdapter(postList)
        progressBar.visibility = View.INVISIBLE
    }


    /**
     * Sets user details in userDetailsView; using the values in userModel
     */
    private fun setUserDetailsInUI(user: User) {
        context?.apply {
            userID.text = this.getString(R.string.user_id_text, user.id.toString())
            name.text = this.getString(R.string.user_name_text, user.name)
            userName.text = this.getString(R.string.userName_name_text, user.username)
            userPhone.text = this.getString(R.string.user_phone_text, user.phone)
            userEmail.text = user.email
            userWebsite.text = user.website
        }
    }

    /**
     * Updates the postDetailsAdapter with postList returned by the remote source.
     * @param postList
     */
    private fun updatePostDetailsAdapter(postList: List<Post>?) {
        if (postList != null) {
            postDetailsAdapter.updatePostDetailsList(postList)
        } else {
            Log.e(LOGTAG, "PostList returned from remote source is null.")
        }
    }
}