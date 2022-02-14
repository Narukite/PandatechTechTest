package com.unknowncompany.pandatechtechtest.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.unknowncompany.pandatechtechtest.databinding.FragmentUsersBinding
import com.unknowncompany.pandatechtechtest.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UsersFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentUsersBinding.inflate(inflater, container, false)

        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = UsersAdapter(viewModel.users)
        }

        val navController = findNavController()
        binding.ivBack.setOnClickListener {
            navController.navigateUp()
        }

        return binding.root
    }

}