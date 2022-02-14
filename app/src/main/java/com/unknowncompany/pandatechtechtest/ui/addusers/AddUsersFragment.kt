package com.unknowncompany.pandatechtechtest.ui.addusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.unknowncompany.pandatechtechtest.R
import com.unknowncompany.pandatechtechtest.databinding.FragmentAddUsersBinding
import com.unknowncompany.pandatechtechtest.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AddUsersFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentAddUsersBinding.inflate(inflater, container, false)

        binding.tvNoUsers.visibility = if (viewModel.users.isEmpty()) VISIBLE else GONE
        val addUsersAdapter = AddUsersAdapter(viewModel.users) { isEmpty ->
            binding.tvNoUsers.visibility = if (isEmpty) VISIBLE else GONE
        }
        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = addUsersAdapter
        }

        val navController = findNavController()
        binding.ivSave.setOnClickListener {
            viewModel.users = addUsersAdapter.getUsers()
            navController.navigate(R.id.navigation_users)
        }
        binding.fabUsers.setOnClickListener {
            addUsersAdapter.addUsers()
        }

        return binding.root
    }

}