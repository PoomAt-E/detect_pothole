package com.detect_pothole.detect_pothole.global.result

enum class ResultCode(
    val code: String,
    val message: String
) {
    // GeoTab
    GEOTAB_REGISTER_SUCCESS("GEO001", "지역 정보 등록 성공"),
    GEOTAB_SEARCH_SUCCESS("GEO002", "지역 정보 조회 성공"),
    GEOTAB_UPDATE_SUCCESS("GEO003", "지역 정보 수정 성공"),

    // Pothole
    POTHOLE_REGISTER_SUCCESS("POT001", "포트홀 등록 성공"),
    POTHOLE_SEARCH_SUCCESS("POT002", "포트홀 조회 성공"),
    POTHOLE_UPDATE_SUCCESS("POT003", "포트홀 수정 성공"),
}