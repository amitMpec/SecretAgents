package com.example.secretagents.data.model.users

import com.example.secretagents.domain.model.GetResponseDataForUI
import com.google.gson.annotations.SerializedName

data class UsersDataDTO(
    @SerializedName("data") var data: Data? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("statusCode") var statusCode: Int? = null,
    @SerializedName("success") var success: Boolean? = null
)

fun UsersDataDTO.toDomain(): GetResponseDataForUI {
    return GetResponseDataForUI(
        data = data!!
    )
}