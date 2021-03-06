package com.example.jsonplaceholder.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.data.api.RetrofitInstance
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.User
import com.example.jsonplaceholder.viewModels.UserListViewModel

/**
 * Shows user's names list fetched from remote source.
 */
class UserListFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private val LOGTAG: String = "UserList"
    private lateinit var userListAdapter: ArrayAdapter<String>
    private lateinit var userListView: ListView
    private var userNameList: ArrayList<String> = ArrayList()
    private var userList: List<User> = ArrayList<User>()
    private lateinit var userListViewModel: UserListViewModel
    private var userEndpointInstance: UserEndpoints? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_list, container, false)
        progressBar = rootView.findViewById(R.id.progress_bar)
        userListView = rootView.findViewById(R.id.user_list)

        userEndpointInstance = RetrofitInstance.getInstance()?.create(UserEndpoints::class.java)
        if (userEndpointInstance != null) {
            //To not set the userList again, if it is already set.
            if (userList.isNullOrEmpty()) {
                userListViewModel = UserListViewModel(userEndpointInstance!!)
                userListViewModel.getUserList.observe(
                    viewLifecycleOwner,
                    { userList -> initListInUI(userList) })
            } else {
                initListInUI(userList)
                Log.i(LOGTAG, "UserList is already fetched.")
                progressBar.visibility = View.INVISIBLE
            }

        } else {
            Log.e(LOGTAG, "UserEndpoint not created successfully.")
        }
        return rootView
    }

    /**
     * Updates the UI with userList and hides progressbar
     * @param userList
     */
    private fun initListInUI(userList: List<User>) {
        setAdapter()
        setListViewItemClickListener()
        updateUserListView(userList)
        progressBar.visibility = View.INVISIBLE
    }

    /**
     * Sets list item click listener for items in our userListView.
     * On click, open and show the respective user's details and posts' details by them.
     */
    private fun setListViewItemClickListener() {
        userListView.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(requireContext(), "Username : $selectedItem", Toast.LENGTH_SHORT).show()
            val bundle = bundleOf(
                "userDetails" to userList[position]
            )
            if (findNavController().currentDestination?.id == R.id.userListFragment) {
                findNavController().navigate(
                    R.id.action_userListFragment_to_userPostDetailsFragment,
                    bundle
                )
            } else {
                Log.i(LOGTAG, " destination ${findNavController().currentDestination?.id}")
            }
        }
    }

    /**
     * Sets adapter for userListView
     */
    private fun setAdapter() {
        userListAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, userNameList
        )
        userListView.adapter = userListAdapter
    }

    /**
     * Updates the userListView with list of posts that are returned by remote source.
     * @param userList
     */
    private fun updateUserListView(userList: List<User>) {
        this.userList = userList
        if (userNameList.isEmpty()) {
            userNameList.addAll(userList.map { user: User -> user.name }
                .toTypedArray())
            userListAdapter.notifyDataSetChanged()
        } else {
            Log.i(LOGTAG, "User name list is already set.")
        }

    }
}