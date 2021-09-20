package ru.sber.generics

// 1.
fun compare(p1: Pair<Any, Any>, p2: Pair<Any, Any>): Boolean {

    return (p1.first == p2.first && p1.second == p2.second)
}

// 2.
fun countGreaterThan(anArray: Array<Comparable<*>> , elem: Comparable<*>): Int {
    var counter = 0
    anArray.forEach { E -> if (compareValues(E, elem) > 0) counter++}
    return counter
}

// 3.
class Sorter<T: Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    val list: MutableList<T> = mutableListOf()

    fun push(element: T) {
        list.add(element)
    }

    fun pop(): T? {
        return list.removeLastOrNull()
    }

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    fun size(): Int {
        return list.size
    }

    fun clear() {
        list.clear()
    }
}