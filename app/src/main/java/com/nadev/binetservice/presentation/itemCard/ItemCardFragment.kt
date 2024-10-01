package com.nadev.binetservice.presentation.itemCard

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nadev.binetservice.R
import com.nadev.binetservice.data.network.Network
import com.nadev.binetservice.databinding.FragmentItemCardBinding
import com.nadev.binetservice.presentation.MainActivity
import com.nadev.binetservice.presentation.itemCard.viewModel.ItemCardViewModel
import com.nadev.binetservice.presentation.itemCard.viewModel.ItemCardViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ItemCardFragment : Fragment() {

    private var _binding: FragmentItemCardBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ItemCardViewModelFactory

    private val viewModel: ItemCardViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt(MainActivity.KEY_ITEM_ID_BUNDLE)

        if (id != null) {
            viewLifecycleOwner.lifecycleScope.launch {
                val drugsData = viewModel.getDrug(id)

                Glide.with(requireContext()).load(Network.BASE_URL.plus(drugsData.image)).listener(
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.isVisible = false
                            return false
                        }
                    }
                ).into(binding.imageDrug)

                binding.textDrugName.text = drugsData.name
                binding.textDrugDescription.text = drugsData.description
            }
        } else {
            Toast.makeText(requireContext(), getText(R.string.error_get_drug), Toast.LENGTH_LONG).show()
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}