package com.example.day8

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel():ViewModel(){
    // private variable
    private val _repository: CounterRepository = CounterRepository()
    private var _count = mutableStateOf(_repository.getCounter().count);

    //Expose the count as an immutable state
    val count: MutableState<Int> = _count

    fun increment(){
        _repository.incremetCounter()
        _count.value = _repository.getCounter().count
    }

    fun decrement(){
        _repository.decrementCounter()
        _count.value = _repository.getCounter().count
    }
}