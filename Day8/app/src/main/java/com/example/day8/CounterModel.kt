package com.example.day8

data class CounterModel(var count: Int)

class CounterRepository{
    private var _counter = CounterModel(0)

    fun getCounter() = _counter

    fun incremetCounter(){
        _counter.count++
    }
    fun decrementCounter(){
        _counter.count--
    }
}