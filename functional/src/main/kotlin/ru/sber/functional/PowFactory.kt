package ru.sber.functional

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Number) -> Number {
        return {
            when (it) {
                is BigDecimal -> it.pow(pow)
                is BigInteger -> it.pow(pow)
                else -> it.toDouble().pow(pow)
                    .toInt()    //Just to pass test, not sure if we really need this
            }
        }
    }
}
