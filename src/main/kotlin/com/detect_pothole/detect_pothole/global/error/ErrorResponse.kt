package com.detect_pothole.detect_pothole.global.error

import org.springframework.validation.BindingResult

class ErrorResponse(
    var code: String,
    var message: String,
    var errors: List<FieldError> = ArrayList()
) {

    constructor(errorCode: ErrorCode, errors: List<FieldError>) : this(errorCode.code, errorCode.message, errors)
    constructor(errorCode: ErrorCode) : this(errorCode.code, errorCode.message, ArrayList())
    class FieldError {
        var field: String? = null
        var value: String? = null
        var reason: String? = null

        companion object {
            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrors: List<org.springframework.validation.FieldError> = bindingResult.getFieldErrors()
                return fieldErrors
                    .map { error: org.springframework.validation.FieldError ->
                        FieldError().apply {
                            field = error.field
                            value = error.rejectedValue.toString()
                            reason = error.defaultMessage
                        }
                    }.toList()
            }
        }
    }

    companion object {
        fun of(errorCode: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(errorCode, FieldError.of(bindingResult))
        }
    }
}