package com.elka.foody.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elka.foody.databinding.BasketFragmentBinding
import com.elka.foody.databinding.MenuFragmentBinding
import com.elka.foody.databinding.ProfileFragmentBinding

class BasketFragment: Fragment() {
  private lateinit var binding: BasketFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = BasketFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }
}