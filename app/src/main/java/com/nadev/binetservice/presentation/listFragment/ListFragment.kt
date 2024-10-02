package com.nadev.binetservice.presentation.listFragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.textfield.TextInputEditText
import com.nadev.binetservice.R
import com.nadev.binetservice.databinding.FragmentListBinding
import com.nadev.binetservice.presentation.MainActivity
import com.nadev.binetservice.presentation.listFragment.adapter.ListAdapter
import com.nadev.binetservice.presentation.listFragment.viewModel.ListViewModel
import com.nadev.binetservice.presentation.listFragment.viewModel.ListViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter = ListAdapter{ onClick(it) }

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    private val viewModel: ListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchField = requireActivity().findViewById<TextInputEditText>(R.id.edit_text_search)
        binding.recyclerDrugs.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.drugs.collect {
                adapter.submitData(it)
            }
        }

        searchField.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {

                viewLifecycleOwner.lifecycleScope.launch {
                    if (searchField.text.toString() == "") {
                        viewModel.changeRecyclerData(null)
                        viewModel.drugs.collect {
                            adapter.submitData(it)
                        }
                    } else {
                        viewModel.changeRecyclerData(searchField.text.toString())
                        viewModel.drugs.collect {
                            adapter.submitData(it)
                        }
                    }
                }
                return@setOnKeyListener true
            } else {
                return@setOnKeyListener false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect{
                binding.progressBar.isVisible = it.refresh is LoadState.Loading
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(MainActivity.KEY_ITEM_ID_BUNDLE, id)
        findNavController().navigate(R.id.action_listFragment_to_itemCardFragment, bundle)

    }
}