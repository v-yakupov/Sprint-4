class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(101, "Недопустимая длина поля"),
    INVALID_PHONE_NUMBER(102, "Недопустимый номер телефона"),
    INVALID_EMAIL(103, "Недопустимый адрес электронной почты"),
    INVALID_SNILS(104, "Недопустимый номер СНИЛС"),
    EMPTY_VALUE(105, "Пустое поле")
}