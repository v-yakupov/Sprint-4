package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StudentsGroupTest {

    @Test
    fun `filterByPredicate should return students with average rate less than 4,9`() {
        val students = listOf(
            Student(firstName = "Robert", lastName = "Heinlein", middleName = "Anson", age = 20, averageRate = 4.8),
            Student(firstName = "Arthur", lastName = "Clark", middleName = "Charles", age = 21, averageRate = 4.7),
            Student(firstName = "Stanisław", lastName = "Lem", averageRate = 4.8, prevEducation = "Lwów University")
        )

        assertEquals(students, StudentsGroup().filterByPredicate { Student -> Student.averageRate < 4.9})
    }

    @Test
    fun `filterByPredicate should return students with non-null prevEducation`() {
        val students = listOf(
            Student(firstName = "Stanisław", lastName = "Lem", averageRate = 4.8, prevEducation = "Lwów University")
        )

        assertEquals(students, StudentsGroup().filterByPredicate { Student -> Student.prevEducation != null})
    }

    @Test
    fun `filterByPredicate should return students with undefined age`() {
        val students = listOf(
            Student(firstName = "Isaac", lastName = "Asimov", averageRate = 4.9),
            Student(firstName = "Stanisław", lastName = "Lem", averageRate = 4.8, prevEducation = "Lwów University")
        )

        assertEquals(students, StudentsGroup().filterByPredicate { Student -> Student.age == 0})
    }
}