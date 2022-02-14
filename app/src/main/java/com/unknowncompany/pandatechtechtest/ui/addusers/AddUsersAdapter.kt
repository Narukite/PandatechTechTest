package com.unknowncompany.pandatechtechtest.ui.addusers

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unknowncompany.pandatechtechtest.R
import com.unknowncompany.pandatechtechtest.databinding.ItemAddUsersBinding
import com.unknowncompany.pandatechtechtest.domain.model.User
import com.unknowncompany.pandatechtechtest.utils.ObservableUtils.getEmailIsInvalidStream
import com.unknowncompany.pandatechtechtest.utils.ObservableUtils.getTextIsBlankStream

class AddUsersAdapter(
    users: List<User>,
    private val emptyUsersCallback: (isEmpty: Boolean) -> Unit,
) :
    RecyclerView.Adapter<AddUsersAdapter.ViewHolder>() {

    private val users = ArrayList<User>().apply {
        addAll(users)
    }

    fun addUsers() {
        users.add(User())
        emptyUsersCallback.invoke(users.isEmpty())
        notifyItemInserted(users.lastIndex)
    }

    fun getUsers(): List<User> = users

    private fun deleteUsers(position: Int) {
        users.removeAt(position)
        if (users.isEmpty()) emptyUsersCallback.invoke(users.isEmpty())
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, users.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAddUsersBinding.bind(itemView)

        @SuppressLint("CheckResult")
        fun bind(position: Int) {
            Log.d("Cek", "bind: $position")
            binding.edUsername.setText(users[position].username)
            val usernameDisposable =
                binding.edUsername.getTextIsBlankStream().subscribe({ isEmpty ->
                    with(binding.tilUsername) {
                        errorIconDrawable = null
                        error = if (isEmpty) context.getString(R.string.username_is_empty) else null
                        isErrorEnabled = isEmpty
                        users[position].username =
                            if (isEmpty) "" else binding.edUsername.text.toString()
                    }
                }, { error ->
                    Log.d(AddUsersAdapter::class.java.simpleName, "bind: ${error.message}")
                })
            binding.edEmail.setText(users[position].email)
            val emailDisposable =
                binding.edEmail.getEmailIsInvalidStream().subscribe({ isNotValid ->
                    with(binding.tilEmail) {
                        errorIconDrawable = null
                        error =
                            if (isNotValid) context.getString(R.string.email_is_not_valid) else null
                        isErrorEnabled = isNotValid
                        users[position].email =
                            if (isNotValid) "" else binding.edEmail.text.toString()
                    }
                }, { error ->
                    Log.d(AddUsersAdapter::class.java.simpleName, "bind: ${error.message}")
                })
            binding.ivDelete.setOnClickListener {
                usernameDisposable.dispose()
                emailDisposable.dispose()
                deleteUsers(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_add_users, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int =
        users.size
}