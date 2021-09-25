package ru.sber.generics

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {

    return (p1.first == p2.first && p1.second == p2.second)
}

// 2.
fun <T: Comparable<T>>countGreaterThan(anArray: Array<T>, elem: T): Int {
    var counter = 0
    anArray.forEach { e -> if (compareValues(e, elem) > 0) counter++}
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
    private val list: MutableList<T> = mutableListOf()

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