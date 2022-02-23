package com.example.jsonplaceholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostDetailsAdapter(private var postDetailsList: List<PostModel>) :
    RecyclerView.Adapter<PostDetailsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var userIDTextView: TextView
        var postIDTextView: TextView
        var titleTextView: TextView
        var bodyTextView: TextView

        init {
            userIDTextView = view.findViewById(R.id.userID)
            postIDTextView = view.findViewById(R.id.postID)
            titleTextView = view.findViewById(R.id.title)
            bodyTextView = view.findViewById(R.id.body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_details_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userIDText = "UserID : " + postDetailsList[position].userId.toString()
        val postIDText = "PostID : " + postDetailsList[position].id.toString()
        val postTitleText = "Title : " + postDetailsList[position].title
        val postBodyText = "Body : " + postDetailsList[position].body
        holder.userIDTextView.text = userIDText
        holder.postIDTextView.text = postIDText
        holder.titleTextView.text = postTitleText
        holder.bodyTextView.text = postBodyText
    }

    override fun getItemCount(): Int {
        return postDetailsList.size
    }

    fun updatePostDetailsList(postList: List<PostModel>) {
        postDetailsList = postList
        notifyDataSetChanged()

    }

}