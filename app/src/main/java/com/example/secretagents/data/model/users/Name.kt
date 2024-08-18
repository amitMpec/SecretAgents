package com.example.secretagents.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val first: String,
    val last: String,
    val title: String
)