package com.example.stand_up.data.networkvos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorVO (
    @SerializedName("code")
    @Expose
    var code: Int = 0,

    @SerializedName("message")
    @Expose
    var message: String = "",

    @SerializedName("info")
    @Expose
    var info: String = ""
)