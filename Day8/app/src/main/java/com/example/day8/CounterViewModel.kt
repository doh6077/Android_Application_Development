package com.example.day8

import androidx.lifecycle.ViewModel

class CounterViewModel:ViewModel(){
    private var _count =0;

    fun increment(){
        _count += 1
    }

    fun decrement(){
        _count -= 1
    }
}