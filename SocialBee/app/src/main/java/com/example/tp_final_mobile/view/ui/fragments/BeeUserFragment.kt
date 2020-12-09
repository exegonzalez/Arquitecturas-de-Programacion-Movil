package com.example.tp_final_mobile.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tp_final_mobile.R
import com.example.tp_final_mobile.model.BeeUser
import com.example.tp_final_mobile.view.adapter.BeeUserAdapter
import com.example.tp_final_mobile.view.adapter.BeeUserListener
import com.example.tp_final_mobile.viewmodel.BeeUserViewModel
import kotlinx.android.synthetic.main.fragment_bee_user.*

class BeeUserFragment : Fragment(), BeeUserListener {

    private lateinit var beeUserAdapter: BeeUserAdapter
    private lateinit var viewModel: BeeUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bee_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(BeeUserViewModel::class.java)
        viewModel.refresh()
        beeUserAdapter = BeeUserAdapter(this)

        rvBeeUser.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = beeUserAdapter
        }

        observerViewModel()

    }

    fun observerViewModel() {
        viewModel.listBeeUsers.observe(viewLifecycleOwner, Observer<List<BeeUser> > {
                beeuser -> beeUserAdapter.updateData(beeuser)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if(it != null)
                rlBaseBeeUser.visibility = View.INVISIBLE
        })
    }

    override fun onBeeUserClicked(beeuser: BeeUser, position: Int) {
        val bundle = bundleOf("beeuser" to beeuser)
        findNavController().navigate(R.id.beeUserDetailDialogFragment, bundle)
    }

}