package com.example.stand_up.data.responses

import com.example.stand_up.data.networkvos.ErrorVO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("error")
    @Expose
    var error: ErrorVO? = null
}