package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "N/A",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "N/A",
    val specialization: String = "N/A",
    val prevEducation: String? = null,
)
