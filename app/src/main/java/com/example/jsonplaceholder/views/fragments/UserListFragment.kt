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
import com.example.jsonplaceholder.data.models.UserModel
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
    private lateinit var userModelList: List<UserModel>
    private lateinit var viewModel: UserListViewModel
    private lateinit var userEndpointInstance: UserEndpoints


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_list, container, false)
        progressBar = rootView.findViewById(R.id.progress_bar)
        userListView = rootView.findViewById(R.id.user_list)

        userEndpointInstance = RetrofitInstance.getInstance()?.create(UserEndpoints::class.java)!!
        viewModel = UserListViewModel(userEndpointInstance)
        viewModel.getUserList.observe(
            viewLifecycleOwner,
            { userList -> updateUserListView(userList) })
        setAdapter()
        setListViewItemClickListener()

        return rootView
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
                "userDetails" to userModelList[position]
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
    private fun updateUserListView(userList: List<UserModel>) {
        userModelList = userList
        Log.i(LOGTAG, " userList $userList")
        userNameList.clear()
        userNameList.addAll(userList.map { userModel: UserModel -> userModel.name }
            .toTypedArray())
        userListAdapter.notifyDataSetChanged()
        progressBar.visibility = View.INVISIBLE

    }
}