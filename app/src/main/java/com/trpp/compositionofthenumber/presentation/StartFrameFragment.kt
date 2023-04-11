package com.trpp.compositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.compositionofthenumber.databinding.FragmentStartFrameBinding

class StartFrameFragment: Fragment() {

    private lateinit var binding: FragmentStartFrameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartFrameBinding.inflate(inflater, container, false)
        return binding.root
    }

}