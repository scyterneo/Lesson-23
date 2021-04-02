package com.ma.p23.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ma.p23.R
import com.ma.p23.data.User

class UserAdapter(private var users: MutableList<User> = mutableListOf()) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_user, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = users[position]
        viewHolder.userNameText.text = user.name
        viewHolder.userImage.setImageResource(user.image)
        viewHolder.container.setOnClickListener {
            users.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = users.size

    fun setUsers(users: List<User>) {
        this.users = users as MutableList<User>
        notifyDataSetChanged()
    }

    fun addUser(user: User) {
        users.add(user)
        notifyItemInserted(users.size)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userNameText: TextView = view.findViewById(R.id.name)
        val userImage: ImageView = view.findViewById(R.id.image)
        val container: View = view.findViewById(R.id.user_container)
    }
}