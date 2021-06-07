package com.example.stand_up.networks

data class ApiResponse<T> (
    var data: T? = null,
    var error: Throwable? = null
)