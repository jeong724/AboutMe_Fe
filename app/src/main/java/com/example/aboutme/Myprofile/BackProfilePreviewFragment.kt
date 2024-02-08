package com.example.aboutme.Myprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aboutme.R
import com.example.aboutme.databinding.FragmentBackprofileBinding
import com.example.aboutme.databinding.FragmentFrontprofileBinding

class BackProfilePreviewFragment: Fragment() {

    lateinit var binding: FragmentBackprofileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBackprofileBinding.inflate(inflater, container, false)

        binding.turnBtn2.setOnClickListener {
            val ft = requireActivity().supportFragmentManager.beginTransaction()

            ft.replace(R.id.profile_frame2, FrontProfilePreviewFragment()).commit()
        }


        return binding.root
    }
}