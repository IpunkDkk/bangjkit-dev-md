package com.example.aplikasigithubuser.detail.follow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasigithubuser.UserAdapter
import com.example.aplikasigithubuser.data.model.ResponseGithubUser
import com.example.aplikasigithubuser.databinding.FragmentFollowsBinding
import com.example.aplikasigithubuser.detail.DetailViewModel


class FollowsFragment : Fragment() {


    private var binding: FragmentFollowsBinding? = null
    private val adapter by lazy {
        UserAdapter{

        }
    }
    private val viewModel by activityViewModels<DetailViewModel>()
    var type = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvFollows?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = this@FollowsFragment.adapter
        }
        when(type){
            FOLLOWERS -> {
                viewModel.resultFollowersUser.observe(viewLifecycleOwner, this::manageResultFollows)
            }
            FOLLOWING -> {
                viewModel.resultFollowingUser.observe(viewLifecycleOwner, this::manageResultFollows)
            }
        }

    }

    private fun manageResultFollows(state: com.example.aplikasigithubuser.utils.Result){
        when(state){
            is com.example.aplikasigithubuser.utils.Result.Success<*> -> {
                adapter.setData(state.data as MutableList<ResponseGithubUser.Item>)
            }
            is com.example.aplikasigithubuser.utils.Result.Error -> {
                Toast.makeText(requireActivity(), state.exception.message.toString(), Toast.LENGTH_SHORT).show()
            }
            is com.example.aplikasigithubuser.utils.Result.Loading -> {
                binding?.progressBar?.isVisible = state.isLoading
            }
        }
    }

    companion object {
        const val FOLLOWING = 100
        const val FOLLOWERS = 101
        fun newInstance(type: Int) = FollowsFragment()
            .apply {
                this.type = type
            }
    }
}