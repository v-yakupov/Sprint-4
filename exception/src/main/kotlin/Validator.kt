abstract class Validator<T> {
    protected val errorList = ArrayList<ErrorCode>()
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val phoneRegexValid = Regex("^([78])\\d{10}$")
        val phoneRegexInvalid = Regex("[\\p{L}\\p{Z}\\p{M}\\p{P}\\p{C}]")

        if (value.isNullOrBlank()) errorList.add(ErrorCode.EMPTY_VALUE) else {
            if (value.length != 11) errorList.add(ErrorCode.INVALID_LENGTH)
            if (value.matches(phoneRegexInvalid)) errorList.add(ErrorCode.INVALID_CHARACTER)
            if (!value.matches(phoneRegexValid)) errorList.add(ErrorCode.INVALID_PHONE_NUMBER)
        }

        return errorList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val emailRegex = Regex("^[a-z]+@[a-z]+\\.[a-z]+$", RegexOption.IGNORE_CASE)

        if (value.isNullOrBlank()) errorList.add(ErrorCode.EMPTY_VALUE) else {
            if (value.length > 32) errorList.add(ErrorCode.INVALID_LENGTH)
            if (!value.matches(emailRegex)) errorList.add(ErrorCode.INVALID_EMAIL)
        }

        return errorList
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val nameRegexValid = Regex("^[а-я]+$", RegexOption.IGNORE_CASE)

        if (value.isNullOrBlank()) errorList.add(ErrorCode.EMPTY_VALUE) else {
            if (value.length > 16) errorList.add(ErrorCode.INVALID_LENGTH)
            if (!value.matches(nameRegexValid)) errorList.add(ErrorCode.INVALID_CHARACTER)
        }

        return errorList
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val snilsRegexInvalid = Regex("[\\p{L}\\p{Z}\\p{M}\\p{P}\\p{C}]")

        if (value.isNullOrBlank()) errorList.add(ErrorCode.EMPTY_VALUE) else {
            if (value.matches(snilsRegexInvalid)) errorList.add(ErrorCode.INVALID_CHARACTER)
            if (value.length != 11) errorList.add(ErrorCode.INVALID_LENGTH)
            if (!isSnilsValid(value)) errorList.add(ErrorCode.INVALID_SNILS)
        }

        return errorList
    }

    private fun isSnilsValid(snils: String): Boolean {
        val snilsRegex = Regex("^\\d{11}$")
        val checksumGiven = snils.substring(9..10).toInt()
        var checksumComputed = 0

        for (i in 0 until 9) {
            checksumComputed += Character.getNumericValue(snils[i]) * (9 - i)
        }

        if (snils.matches(snilsRegex)) {
            if (checksumComputed < 100) return checksumComputed == checksumGiven
            if (checksumComputed == 100) return checksumGiven == 0
            if (checksumComputed > 100) return checksumComputed % 101 == checksumGiven
        }
        return false
    }
}