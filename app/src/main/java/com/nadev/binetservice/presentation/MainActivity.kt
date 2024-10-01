package com.nadev.binetservice.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.nadev.binetservice.R
import com.nadev.binetservice.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputLayoutSearch.setEndIconOnClickListener {
            binding.editTextSearch.isVisible = !binding.editTextSearch.isVisible
            binding.inputLayoutSearch.isHintEnabled = binding.editTextSearch.isVisible
        }

        binding.buttonBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            if (destination.id == R.id.itemCardFragment) {
                binding.textScreenName.isVisible = false
                binding.inputLayoutSearch.isVisible = false
            } else {
                binding.textScreenName.isVisible = true
                binding.inputLayoutSearch.isVisible = true
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KEY_ITEM_ID_BUNDLE = "id"
    }
}