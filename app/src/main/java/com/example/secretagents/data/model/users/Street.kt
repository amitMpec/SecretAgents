package com.example.secretagents.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Street(
    val name: String,
    val number: Int
)