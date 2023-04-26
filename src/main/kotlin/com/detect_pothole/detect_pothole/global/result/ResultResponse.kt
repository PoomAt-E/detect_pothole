package com.detect_pothole.detect_pothole.global.result

class ResultResponse(
    resultCode: ResultCode,
    val data: Any
) {
    constructor(resultCode: ResultCode): this(resultCode, "")

    val code: String
    val message: String

    init {
        this.code = resultCode.code
        message = resultCode.message
    }

    companion object {
        fun of(resultCode: ResultCode, data: Any): ResultResponse {
            return ResultResponse(resultCode, data)
        }

        fun of(resultCode: ResultCode): ResultResponse {
            return ResultResponse(resultCode, "")
        }
    }
}