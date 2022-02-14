package com.unknowncompany.pandatechtechtest.ui.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unknowncompany.pandatechtechtest.R
import com.unknowncompany.pandatechtechtest.databinding.ItemUsersBinding
import com.unknowncompany.pandatechtechtest.domain.model.User

class UsersAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUsersBinding.bind(itemView)

        @SuppressLint("CheckResult")
        fun bind(position: Int) {
            with(binding) {
                tvInisial.text =
                    if (users[position].username.isNotBlank()) users[position].username[0].toString() else ""
                tvUsername.text = users[position].username
                tvEmail.text = users[position].email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_users, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int =
        users.size
}