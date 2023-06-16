package com.elka.foody.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elka.foody.databinding.MenuFragmentBinding

class MenuFragment: Fragment() {
  private lateinit var binding: MenuFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = MenuFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }
}