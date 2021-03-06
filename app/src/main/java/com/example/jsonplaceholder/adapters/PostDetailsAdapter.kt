package com.example.jsonplaceholder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.data.models.Post

/**
 * Adapter to show user's post's details in a list.
 * @param postDetailsList : List<PostModel> which is a list of posts' with all the details.
 */
class PostDetailsAdapter(private var postDetailsList: ArrayList<Post>) :
    RecyclerView.Adapter<PostDetailsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var userIDTextView: TextView = view.findViewById(R.id.user_ID)
        var postIDTextView: TextView = view.findViewById(R.id.post_ID)
        var titleTextView: TextView = view.findViewById(R.id.title)
        var bodyTextView: TextView = view.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_details_item_view, parent, false)
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

    /**
     * Updates posts list in adapter
     * @param postList: list of posts
     */
    fun updatePostDetailsList(postList: List<Post>) {
        postDetailsList.clear()
        postDetailsList.addAll(postList)
        notifyDataSetChanged()

    }

}