package com.example.secretagents.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Dob(
    val age: Int,
    val date: String
)