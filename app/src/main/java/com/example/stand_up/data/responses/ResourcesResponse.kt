package com.example.stand_up.data.responses

import com.example.stand_up.data.networkvos.ResourcesVO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResourcesResponse: BaseResponse() {
    @SerializedName("data")
    @Expose
    var data: ResourcesVO? = null
}