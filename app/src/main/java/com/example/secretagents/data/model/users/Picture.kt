package com.example.secretagents.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)