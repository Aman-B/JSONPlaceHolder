package com.example.jsonplaceholder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


class UserListFragment : Fragment() {
    private lateinit var userListAdapter: ArrayAdapter<String>
    private lateinit var mListView: ListView
    private var userNameList: ArrayList<String> = ArrayList()
    private lateinit var userModelList: List<UserModel>
    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_list, container, false)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        mListView = rootView.findViewById(R.id.userlist)
        setAdapter()
        setListViewItemClickListener()
        viewModel.userList.observe(
            viewLifecycleOwner,
            Observer { userList -> updateUserListView(userList) })
        return rootView
    }

    private fun setListViewItemClickListener() {
        mListView.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(requireContext(), "Username : " + selectedItem, Toast.LENGTH_LONG).show()
            val bundle = bundleOf(
                "id" to userModelList[position].id,
                "name" to userModelList[position].name,
                "username" to userModelList[position].username,
                "email" to userModelList[position].email,
                "phone" to userModelList[position].phone,
                "website" to userModelList[position].website
            )
            if (findNavController().currentDestination?.id == R.id.userListFragment) {
                val action =
                    UserListFragmentDirections.actionUserListFragmentToUserPostDetailsFragment()
                findNavController().navigate(
                    R.id.action_userListFragment_to_userPostDetailsFragment,
                    bundle
                )
            } else {
                Log.i("destination ", " " + findNavController().currentDestination?.id)
            }
        }
    }

    private fun setAdapter() {
        userListAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, userNameList
        )
        mListView.adapter = userListAdapter
    }

    private fun updateUserListView(userList: List<UserModel>) {
        userModelList = userList
        Log.i("amanTag", " user " + userList)
        userNameList.clear()
        userNameList.addAll(userList.map { userModel: UserModel -> userModel.username }
            .toTypedArray())
        userListAdapter.notifyDataSetChanged()

    }
}