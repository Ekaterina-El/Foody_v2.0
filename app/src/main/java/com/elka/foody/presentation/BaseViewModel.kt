package com.elka.foody.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
  private val _works = MutableLiveData<List<Work>>(listOf())
  val works get() = _works

  fun addWork(work: Work) {
    changeWorkList(work, Action.ADD)
  }

  fun removeWork(work: Work) {
    changeWorkList(work, Action.REMOVE)
  }

  private fun changeWorkList(work: Work, action: Action) {
    val items = _works.value!!.toMutableList()
    when (action) {
      Action.ADD -> items.add(work)
      Action.REMOVE -> items.remove(work)
      else -> return
    }
    _works.value = items
  }
}