package ru.sber.functional

class StudentsGroup {

    var students: List<Student> = listOf(
        Student(firstName = "Robert", lastName = "Heinlein", middleName = "Anson", age = 20, averageRate = 4.8),
        Student(firstName = "Arthur", lastName = "Clark", middleName = "Charles", age = 21, averageRate = 4.7),
        Student(firstName = "Isaac", lastName = "Asimov", averageRate = 4.9),
        Student(firstName = "Stanisław", lastName = "Lem", averageRate = 4.8, prevEducation = "Lwów University")
        )

    fun filterByPredicate(condition: (Student) -> Boolean): List<Student> {
        return students.filter(condition)
    }
}
